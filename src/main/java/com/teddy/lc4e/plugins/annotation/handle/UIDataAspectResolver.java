package com.teddy.lc4e.plugins.annotation.handle;

import com.teddy.lc4e.core.web.service.UIData;
import com.teddy.lc4e.plugins.annotation.SetUIDataField;
import com.teddy.lc4e.plugins.annotation.SetUIDataGroup;
import com.teddy.lc4e.plugins.cache.CacheHandler;
import com.teddy.lc4e.plugins.tools.ReflectTool;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.lang.reflect.Method;

/**
 * Created by teddy on 2015/5/20.
 */


@Component
@Aspect
public class UIDataAspectResolver {


    @Autowired
    private UIData uiData;
    @Autowired
    private CacheHandler cacheHandler;

    private boolean useCache;

    @SuppressWarnings("finally")
    @Around("@annotation(com.teddy.lc4e.plugins.annotation.SetUIDataGroup)")
    public Object setUIDataForAn(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean flag = false;
        SetUIDataGroup an = null;
        Object[] args = null;
        Method method = null;
        Object target = null;
        String methodName = null;
        try {
            methodName = joinPoint.getSignature().getName();
            target = joinPoint.getTarget();
            method = ReflectTool.getMethodByClassAndName(target.getClass(), methodName);
            args = joinPoint.getArgs(); // all parameters
            an = (SetUIDataGroup) ReflectTool.getAnnotationByMethod(method, SetUIDataGroup.class);
            flag = setValueByFunctionName(an, args);
        } catch (Exception e) {
            flag = false;
        } finally {
            if (flag) {
                return joinPoint.proceed();
            } else {
                return ReflectTool.returnHandle(method, args, an.modIndex(), "Set UI Data Failed");
            }
        }
    }

    private boolean setValueByFunctionName(SetUIDataGroup setUIDataGroup, Object[] args) {
        useCache = cacheHandler.useCache();
        SetUIDataField[] uiDataFields = setUIDataGroup.fields();
        Model model = (Model) args[setUIDataGroup.modIndex()];
        Class clazz = uiData.getClass();

        for (int i = 0, len = uiDataFields.length; i < len; i++) {
            SetUIDataField curField = uiDataFields[i];
            String functionName = curField.functionName();
            String attributeName = curField.attributeName();
            int[] useVars = curField.useVarIndex();
            Object[] objs = null;
            Object obj = null;
            if (functionName.isEmpty() || attributeName.isEmpty()) {
                return false;
            } else if (functionName.indexOf("get") == -1) {
                functionName = "get" + functionName;
            }
            if (useVars.length != 0) {
                objs = new Object[useVars.length];
                for (int j = 0, lenj = useVars.length; j < lenj; j++) {
                    objs[j] = args[useVars[j]];
                }
            } else if (useCache) {
                obj = cacheHandler.getCache(curField.cacheName(), curField.key());
                if (obj != null ) {
                    model.addAttribute(attributeName, obj);
                    continue;
                }
            }
            Method method = null;
            try {
                method = clazz.getDeclaredMethod(functionName);
                obj = method.invoke(uiData, objs);
                if (obj != null) {
                    if (useCache) {
                        cacheHandler.setCache(curField.cacheName(), curField.key(), obj);
                    }
                    model.addAttribute(attributeName, obj);

                } else {
                    return false;
                }
            } catch (Exception e) {
                e.getCause();
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

}