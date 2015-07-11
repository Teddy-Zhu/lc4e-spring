package com.teddy.lc4e.plugins.annotation.handle;

import com.teddy.lc4e.core.database.model.SysComVar;
import com.teddy.lc4e.core.database.service.ComVarDao;
import com.teddy.lc4e.global.Global;
import com.teddy.lc4e.plugins.annotation.ValidateComVar;
import com.teddy.lc4e.plugins.annotation.ValidateParam;
import com.teddy.lc4e.plugins.annotation.ValidateParams;
import com.teddy.lc4e.plugins.annotation.ValidateToken;
import com.teddy.lc4e.plugins.cache.CacheHandler;
import com.teddy.lc4e.plugins.exception.Lc4eException;
import com.teddy.lc4e.plugins.tools.ReflectTool;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Aspect
public class ValidateAspectResolver {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(ValidateAspectResolver.class);


    @Autowired
    private CacheHandler cacheHandler;

    @Autowired
    private ComVarDao comVarDao;

    private boolean useCache;

    @SuppressWarnings("finally")
    @Before("@annotation(com.teddy.lc4e.plugins.annotation.ValidateParams)")
    public void validateAroundParameter(JoinPoint joinPoint) throws Throwable {
        boolean flag = false;
        ValidateParams an = null;
        Object[] args = null;
        Class<?>[] types = null;
        Method method = null;
        Object target = null;
        String methodName = null;
        try {
            methodName = joinPoint.getSignature().getName();
            target = joinPoint.getTarget();
            method = ReflectTool.getMethodByClassAndName(target.getClass(), methodName);
            types = method.getParameterTypes();
            args = joinPoint.getArgs();
            an = (ValidateParams) ReflectTool.getAnnotationByMethod(method, ValidateParams.class);
            flag = validateFieldBefore(an, args, types);
        } catch (Exception e) {
            flag = false;
        } finally {
            if (!flag) {
                throw new Lc4eException("Parameter validation error");
            }
        }
    }

    @SuppressWarnings("finally")
    @Before("@annotation(com.teddy.lc4e.plugins.annotation.ValidateToken)")
    public void validateAroundToken(JoinPoint joinPoint) throws Throwable {
        boolean flag = false;
        ValidateToken an = null;
        Object[] args = null;
        Method method = null;
        Object target = null;
        String methodName = null;
        try {
            methodName = joinPoint.getSignature().getName();
            target = joinPoint.getTarget();
            method = ReflectTool.getMethodByClassAndName(target.getClass(), methodName);
            args = joinPoint.getArgs();
            an = (ValidateToken) ReflectTool.getAnnotationByMethod(method, ValidateToken.class);
            flag = validateToken(an, args);
        } catch (Exception e) {
            flag = false;
        } finally {
            if (!flag) {
                throw new Lc4eException("Token Auth Error");
            }
        }
    }

    private boolean validateToken(ValidateToken vt, Object[] args) {

        HttpServletRequest request = (HttpServletRequest) args[vt.reqIndex()];

        String urlLen = String.valueOf(request.getRequestURI().length() - 1);
        String lc4eToken = request.getHeader("lc4e-token");

        if (lc4eToken == null || lc4eToken.trim().equals("")) {
            return false;
        } else {
            String regex = "\\d+", unixTime = "";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(lc4eToken);

            while (m.find()) {
                unixTime = m.group();
            }
            if (!unixTime.equals(lc4eToken)) {

                return false;
            }
            regex = "\\b" + urlLen + "(.*)" + urlLen + "\\b";
            p = Pattern.compile(regex);
            m = p.matcher(lc4eToken);

            while (m.find()) {
                unixTime = m.group(1);
            }
            if (unixTime == null || "".equals(unixTime.trim())) {
                return false;
            }
            Long now = new Date().getTime();
            Long diff = now - Long.valueOf(unixTime);
            if (diff < 0) {
                return false;
            }
            Long day = diff / (1000 * 60 * 60 * 24);
            Long hour = (diff / (60 * 60 * 1000) - day * 24);
            Long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            Long second = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

            if (day > 0 || hour > 0 || min > 0 || second > 10) {
                return false;
            }
        }
        return true;
    }

    private boolean validateFieldBefore(ValidateParams vt, Object[] args, Class<?>[] types) throws Lc4eException {
        if (!validateField(vt.fields(), args, types)) {
            return false;
        }

        if (vt.useSelect()) {
            ValidateComVar validateVar = vt.validate();
            useCache = cacheHandler.useCache();
            if (useCache) {
                SysComVar var = (SysComVar) cacheHandler.getCache(Global.VAR, validateVar.name());
                if (var == null) {
                    var = comVarDao.getSysComVarByName(validateVar.name());
                    cacheHandler.setCache(Global.VAR, validateVar.name(), var);
                }
                if (validateValue(var.getValue(), validateVar)) {
                    return validateField(vt.trueFields(), args, types);
                } else {
                    return validateField(vt.falseFields(), args, types);
                }
            } else {
                SysComVar var = var = comVarDao.getSysComVarByName(validateVar.name());
                if (validateValue(var.getValue(), validateVar)) {
                    return validateField(vt.trueFields(), args, types);
                } else {
                    return validateField(vt.falseFields(), args, types);
                }
            }
        }
        return true;
    }

    private boolean validateValue(Object value, ValidateComVar validateValue) {
        if (value instanceof String) {
            return String.valueOf(validateValue.needValue()).equals(value.toString());
        } else if (value instanceof Integer) {
            return (Integer) value == Integer.valueOf(validateValue.needValue());
        } else if (value instanceof Boolean) {
            return (Boolean) value == Boolean.valueOf(validateValue.needValue());
        } else if (value instanceof Float) {
            return (Float) value == Float.valueOf(validateValue.needValue());
        } else if (value instanceof Double) {
            return (Double) value == Double.valueOf(validateValue.needValue());
        }
        return false;
    }


    /**
     * @param valiedateFields
     * @param args
     * @param types
     * @return
     * @throws Lc4eException
     */
    private boolean validateField(ValidateParam[] valiedateFields, Object[] args, Class<?>[] types) throws Lc4eException {
        for (ValidateParam validateField : valiedateFields) {
            int index = validateField.index();
            if ("".equals(validateField.fieldName()) && validateField.index() == -1) {
                continue;
            }
            Object arg = null;

            if ("".equals(validateField.fieldName())) {
                arg = args[index];
            } else {
                try {
                    arg = ReflectTool.getFieldByObjectAndFileName(args[index], validateField.fieldName());
                } catch (Exception e) {
                    throw new Lc4eException("Get Field From Arg Error,Field Name is :" + validateField.fieldName() + " Arg is:" + args[index]);
                }
            }

            arg = ReflectTool.parseDefaultValue(validateField.defaultValue());

            if (validateField.required() && arg == null) {
                return false;
            }


            try {
                if (types[index] == String.class) {
                    arg = String.valueOf(arg);
                    if (validateField.maxLen() > 0 && ((String) arg).length() > validateField.maxLen()) {
                        return false;
                    }

                    if (validateField.minLen() > 0 && ((String) arg).length() < validateField.minLen()) {
                        return false;
                    }
                    if (!"".equals(validateField.regexStr()) && !((String) arg).matches(validateField.regexStr())) {
                        return false;
                    }
                } else if (types[index] == Integer.class) {
                    arg = Integer.valueOf(arg.toString());
                    if (validateField.maxVal() != -1 && ((Integer) arg) > validateField.maxVal()) {
                        return false;
                    }

                    if (validateField.minVal() != -1 && ((Integer) arg) < validateField.minVal()) {
                        return false;
                    }
                } else if (types[index] == Double.class) {
                    arg = Double.valueOf(arg.toString());
                    if (validateField.maxVal() != -1 && ((Integer) arg) > validateField.maxVal()) {
                        return false;
                    }
                    if (validateField.minVal() != -1 && ((Integer) arg) < validateField.minVal()) {
                        return false;
                    }
                } else if (types[index] == Boolean.class) {
                    arg = Boolean.valueOf(arg.toString());
                }
            } catch (Exception e) {
                throw new Lc4eException("Format Default Value ERROR,Value String is" + arg);
            }

        }
        return true;
    }

}
