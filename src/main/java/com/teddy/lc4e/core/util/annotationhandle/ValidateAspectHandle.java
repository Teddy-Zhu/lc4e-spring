package com.teddy.lc4e.core.util.annotationhandle;

import com.teddy.lc4e.core.database.model.SysComVar;
import com.teddy.lc4e.core.database.service.ComVarDao;
import com.teddy.lc4e.core.util.annotation.*;
import com.teddy.lc4e.core.util.cache.CacheHandler;
import com.teddy.lc4e.core.util.common.Global;
import com.teddy.lc4e.core.util.common.ReflectTool;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Aspect
public class ValidateAspectHandle {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(ValidateAspectHandle.class);


    @Autowired
    private CacheHandler cacheHandler;

    @Autowired
    private ComVarDao comVarDao;

    private boolean useCache;

    @SuppressWarnings("finally")
    @Around("@annotation(com.teddy.lc4e.core.util.annotation.ValidateGroup)")
    public Object validateAroundParameter(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean flag = false;
        ValidateGroup an = null;
        Object[] args = null;
        Method method = null;
        Object target = null;
        String methodName = null;
        try {
            methodName = joinPoint.getSignature().getName();
            target = joinPoint.getTarget();
            method = ReflectTool.getMethodByClassAndName(target.getClass(), methodName);
            args = joinPoint.getArgs(); // all parameters
            an = (ValidateGroup) ReflectTool.getAnnotationByMethod(method, ValidateGroup.class);
            flag = validateFieldBefore(an, args);
        } catch (Exception e) {
            flag = false;
        } finally {
            if (flag) {
                return joinPoint.proceed();
            } else {
                return ReflectTool.returnHandle(method, args, an.modIndex(), "Parameter validation error");
            }
        }
    }

    @SuppressWarnings("finally")
    @Around("@annotation(com.teddy.lc4e.core.util.annotation.ValidateToken)")
    public Object validateAroundToken(ProceedingJoinPoint joinPoint) throws Throwable {
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
            args = joinPoint.getArgs(); // all parameters
            an = (ValidateToken) ReflectTool.getAnnotationByMethod(method, ValidateToken.class);
            flag = validateToken(an, args);
        } catch (Exception e) {
            flag = false;
        } finally {
            if (flag) {
                return joinPoint.proceed();
            } else {
                return ReflectTool.returnHandle(method, args, an.modIndex(), "Token Auth Error");
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

    private boolean validateFieldBefore(ValidateGroup vt, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (!validateField(vt.fields(), args)) {
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
                    return validateField(vt.trueFields(), args);
                } else {
                    return validateField(vt.falseFields(), args);
                }
            } else {
                SysComVar var = var = comVarDao.getSysComVarByName(validateVar.name());
                if (validateValue(var.getValue(), validateVar)) {
                    return validateField(vt.trueFields(), args);
                } else {
                    return validateField(vt.falseFields(), args);
                }
            }
        }
        return true;
    }

    private boolean validateValue(Object value, ValidateComVar validateValue) {
        if (value instanceof String) {
            return validateValue.needString().equals(value.toString());
        } else if (value instanceof Integer) {
            return (Integer) value == validateValue.needInt();
        } else if (value instanceof Boolean) {
            return (Boolean) value == validateValue.needBoolean();
        } else if (value instanceof Float) {
            return (Float) value == validateValue.needFloat();
        } else if (value instanceof Double) {
            return (Double) value == validateValue.needDouble();
        }
        return false;
    }

    /**
     * handle parameters
     *
     * @param valiedatefiles
     * @param args
     * @return
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private boolean validateField(ValidateField[] valiedatefiles, Object[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        for (ValidateField validateField : valiedatefiles) {
            if ("".equals(validateField.fieldName()) && validateField.index() == -1){
                continue;
            }
            Object arg = null;
            if ("".equals(validateField.fieldName())) {
                arg = args[validateField.index()];
            } else {
                arg = ReflectTool.getFieldByObjectAndFileName(args[validateField.index()], validateField.fieldName());
            }

            if (validateField.NotNull() && arg == null) {
                return false;
            }

            if (arg instanceof String) {
                if (arg == null) {
                    arg = validateField.defaultString();
                }
                if (validateField.maxLen() > 0 && ((String) arg).length() > validateField.maxLen()) {
                        return false;
                }

                if (validateField.minLen() > 0 && ((String) arg).length() < validateField.minLen()) {
                        return false;
                }
                if (!"".equals(validateField.regexStr()) && !((String) arg).matches(validateField.regexStr())) {
                        return false;
                }
            } else if (arg instanceof Integer) {
                if (arg == null) {
                    arg = validateField.defaultInt();
                }
                if (validateField.maxVal() != -1 && (Integer) arg > validateField.maxVal()) {
                        return false;
                }

                if (validateField.minVal() != -1 && (Integer) arg < validateField.minVal()) {
                        return false;
                }
            } else if (arg instanceof Double) {
                if (arg == null) {
                    arg = validateField.defaultDouble();
                }
            } else if (arg instanceof Boolean) {
                if (arg == null) {
                    arg = validateField.defaultBoolean();
                }
            }
        }
        return true;
    }

}
