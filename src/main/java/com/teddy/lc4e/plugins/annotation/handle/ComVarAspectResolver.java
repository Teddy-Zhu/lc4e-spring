package com.teddy.lc4e.plugins.annotation.handle;

import com.teddy.lc4e.core.database.model.SysComVar;
import com.teddy.lc4e.core.database.service.ComVarDao;
import com.teddy.lc4e.core.entity.back.Str;
import com.teddy.lc4e.global.Global;
import com.teddy.lc4e.plugins.annotation.SetComVar;
import com.teddy.lc4e.plugins.annotation.ValidateComVar;
import com.teddy.lc4e.plugins.annotation.ValidateComVars;
import com.teddy.lc4e.plugins.cache.CacheHandler;
import com.teddy.lc4e.plugins.exception.Lc4eException;
import com.teddy.lc4e.plugins.tools.ReflectTool;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by teddy on 2015/6/14.
 */
@Component
@Aspect
public class ComVarAspectResolver {

    @Autowired
    private ComVarDao comVarDao;
    @Autowired
    private CacheHandler cacheHandler;

    private boolean useCache;

    @SuppressWarnings("finally")
    @Before("@annotation(com.teddy.lc4e.plugins.annotation.ValidateComVars)")
    public void validateComVarAround(JoinPoint joinPoint) throws Throwable {
        boolean flag = false;
        ValidateComVars an = null;
        Object[] args = null;
        Method method = null;
        Object target = null;
        String methodName = null;
        Str errorString = new Str();
        try {
            methodName = joinPoint.getSignature().getName();
            target = joinPoint.getTarget();
            method = ReflectTool.getMethodByClassAndName(target.getClass(), methodName);
            args = joinPoint.getArgs();
            an = (ValidateComVars) ReflectTool.getAnnotationByMethod(method, ValidateComVars.class);
            Assert.noNullElements(args, "Some Variables must be not empty.");
            flag = validateComVar(an.fields(), args, errorString);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            if (!flag) {
                throw new Lc4eException(errorString.s);
            }
        }
    }


    @SuppressWarnings("finally")
    @Before("@annotation(com.teddy.lc4e.plugins.annotation.SetComVar)")
    public void setComVar(JoinPoint joinPoint) throws Throwable {
        boolean flag = false;
        SetComVar an = null;
        Object[] args = null;
        Method method = null;
        Object target;
        String methodName;
        try {
            methodName = joinPoint.getSignature().getName();
            target = joinPoint.getTarget();
            method = ReflectTool.getMethodByClassAndName(target.getClass(), methodName);
            args = joinPoint.getArgs();
            an = (SetComVar) ReflectTool.getAnnotationByMethod(method, SetComVar.class);
            Assert.notEmpty(args, "Some Variables must be not empty.");
            flag = setValueByFunctionName(an, args);
        } catch (Exception e) {
            flag = false;
        } finally {
            if (!flag) {
                throw new Lc4eException("Set Config Value [" + an.comVar().toString() + "] failed");
            }
        }
    }

    private boolean validateComVar(ValidateComVar[] vt, Object[] args, Str errorString) {
        Assert.noNullElements(vt, "the ValidateFields must be not empty.");
        useCache = cacheHandler.useCache();

        Map<String, ValidateComVar> maps = new HashMap<String, ValidateComVar>();
        String[] allNames = new String[vt.length];
        for (int i = 0, len = vt.length; i < len; i++) {
            maps.put(vt[i].name(), vt[i]);
            allNames[i] = vt[i].name();
        }
        if (useCache) {
            List<String> fromDB = new ArrayList<String>();

            for (int i = 0, len = allNames.length; i < len; i++) {
                SysComVar obj = (SysComVar) cacheHandler.getCache(Global.VAR, allNames[i]);
                if (obj == null) {
                    fromDB.add(allNames[i]);
                } else {
                    if (!validateValue(obj.getValue(), maps.get(allNames[i]))) {
                        errorString.s = obj.getError();
                        return false;
                    }
                }
            }
            List<SysComVar> sysComVars = comVarDao.getSysComVarsByNames(fromDB.toArray(new String[fromDB.size()]));
            for (int i = 0, len = sysComVars.size(); i < len; i++) {
                SysComVar current = sysComVars.get(i);
                Object value = current.getValue();
                String name = current.getName();
                cacheHandler.setCache(Global.VAR, name, current);
                if (!validateValue(value, maps.get(name))) {
                    errorString.s = current.getError();
                    return false;
                }
            }
        } else {
            List<SysComVar> sysComVars = comVarDao.getSysComVarsByNames(allNames);
            for (int i = 0, len = sysComVars.size(); i < len; i++) {
                SysComVar current = sysComVars.get(i);
                Object value = current.getValue();
                String name = current.getName();
                if (!validateValue(value, maps.get(name))) {
                    errorString.s = current.getError();
                    return false;
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

    private boolean setValueByFunctionName(SetComVar fields, Object[] objects) {
        Assert.notEmpty(fields.comVar(), "the given ComVars must be not empty");
        useCache = cacheHandler.useCache();
        String[] configs = fields.comVar();
        if (configs.length == 0) {
            return false;
        }
        Model model = (Model) objects[fields.modIndex()];
        Object obj = null;
        if (useCache) {
            List<String> fromDB = new ArrayList<String>();
            for (int i = 0, len = configs.length; i < len; i++) {
                obj = cacheHandler.getCache(fields.cacheName(), configs[i]);
                if (obj != null) {
                    SysComVar tmpVar = (SysComVar) obj;
                    model.addAttribute(configs[i], tmpVar.getValue());
                } else {
                    fromDB.add(configs[i]);
                }
            }
            if (fromDB.size() > 0) {
                List<SysComVar> sysComVars = comVarDao.getSysComVarsByNames(fromDB.toArray(new String[fromDB.size()]));
                for (int i = 0, len = sysComVars.size(); i < len; i++) {
                    SysComVar current = sysComVars.get(i);
                    String key = sysComVars.get(i).getName();
                    cacheHandler.setCache(fields.cacheName(), key, current);
                    model.addAttribute(key, current.getValue());
                }
            }
        } else {
            List<SysComVar> sysComVars = comVarDao.getSysComVarsByNames(configs);
            for (int i = 0, len = sysComVars.size(); i < len; i++) {
                SysComVar current = sysComVars.get(i);
                String key = sysComVars.get(i).getName();
                model.addAttribute(key, current.getValue());
            }
        }
        return true;
    }

}
