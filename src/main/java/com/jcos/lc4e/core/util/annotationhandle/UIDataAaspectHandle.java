package com.jcos.lc4e.core.util.annotationhandle;

import com.alibaba.fastjson.JSONObject;
import com.jcos.lc4e.core.entity.Message;
import com.jcos.lc4e.core.util.annotation.UIDataField;
import com.jcos.lc4e.core.util.annotation.UIDataGroup;
import com.jcos.lc4e.core.util.cache.CacheHandler;
import com.jcos.lc4e.core.web.service.UIData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by teddy on 2015/5/20.
 */


@Component
@Aspect
public class UIDataAaspectHandle {


    @Autowired
    private UIData uiData;
    @Autowired
    private CacheHandler cacheHandler;

    @SuppressWarnings("finally")
    @Around("@annotation(com.jcos.lc4e.core.util.annotation.UIDataGroup)")
    public Object setUIDataForAn(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean flag = false;
        UIDataGroup an = null;
        Object[] args = null;
        Method method = null;
        Object target = null;
        String methodName = null;
        try {
            methodName = joinPoint.getSignature().getName();
            target = joinPoint.getTarget();
            method = getMethodByClassAndName(target.getClass(), methodName);
            args = joinPoint.getArgs(); // all parameters
            an = (UIDataGroup) getAnnotationByMethod(method, UIDataGroup.class);
            flag = setValueByFunctionName(an, args);
        } catch (Exception e) {
            flag = false;
        } finally {
            if (flag) {
                return joinPoint.proceed();
            } else {
                return returnHandle(method, args, an.modIndex(), "Set UI Data Failed");
            }
        }
    }

    private Object returnHandle(Method method, Object[] args, Integer modelIndex, String failed) {
        if (method.getReturnType().getName().equals("java.lang.String")) {
            Model model = (Model) args[modelIndex];
            model.addAttribute("Message", JSONObject.toJSONString(new Message(failed)));
            return "System/Message";
        } else {
            return new Message(failed);
        }
    }

    private boolean setValueByFunctionName(UIDataGroup setUIDataGroup, Object[] args) {
        UIDataField[] uiDataFields = setUIDataGroup.fields();
        Model model = (Model) args[setUIDataGroup.modIndex()];
        Class clazz = uiData.getClass();

        for (int i = 0, len = uiDataFields.length; i < len; i++) {
            UIDataField curField = uiDataFields[i];
            String functionName = curField.functionName();
            String attributeName = curField.attributeName();
            int[] useVars = curField.useVarIndex();
            Object[] objs = null;
            Object obj = null;
            if (functionName.isEmpty() || attributeName.isEmpty()) {
                return false;
            } else if (functionName.indexOf("get") != -1) {
                functionName = "get" + functionName;
            }
            if (useVars.length != 0) {
                objs = new Object[useVars.length];
                for (int j = 0, lenj = useVars.length; j < lenj; j++) {
                    objs[j] = args[useVars[j]];
                }
            } else if (curField.useCache()) {
                obj = cacheHandler.getCache(curField.cacheName(), curField.key());
                if (obj != null) {
                    model.addAttribute(attributeName, obj);
                    continue;
                }
            }
            Method method = null;
            try {
                method = clazz.getDeclaredMethod(functionName);
                obj = method.invoke(uiData, objs);
                if (obj != null) {
                    cacheHandler.setCache(curField.cacheName(), curField.key(), obj);
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

    /**
     * according annotation class to get annotation
     */
    private Annotation getAnnotationByMethod(Method method, Class annoClass) {
        Annotation all[] = method.getAnnotations();
        for (Annotation annotation : all) {
            if (annotation.annotationType() == annoClass) {
                return annotation;
            }
        }
        return null;
    }

    /**
     * according method and class to get method
     */
    private Method getMethodByClassAndName(Class c, String methodName) {
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }
}