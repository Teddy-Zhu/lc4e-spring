package com.teddy.lc4e.plugins.tools;

import com.alibaba.fastjson.JSONObject;
import com.teddy.lc4e.core.entity.webui.Message;
import org.springframework.ui.Model;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by teddy on 2015/6/14.
 */
public class ReflectTool {
    /**
     * according annotation class to get annotation
     */
    public static Annotation getAnnotationByMethod(Method method, Class annoClass) {
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
    public static Method getMethodByClassAndName(Class c, String methodName) {
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }


    public static Object returnHandle(Method method, Object[] args, Integer modelIndex, String failed) {
        if (method.getReturnType().getName().equals("java.lang.String")) {
            Model model = (Model) args[modelIndex];
            model.addAttribute("Message", JSONObject.toJSONString(new Message(failed)));
            return "System/Message";
        } else {
            return new Message(failed);
        }
    }

    public static Object getFieldByObjectAndFileName(Object targetObj, String fileName) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        String tmp[] = fileName.split("\\.");
        Object arg = targetObj;
        for (int i = 0; i < tmp.length; i++) {
            Method methdo = arg.getClass().getMethod(getGetterNameByFieldName(tmp[i]));
            arg = methdo.invoke(arg);
        }
        return arg;
    }

    /**
     * get field get function
     */
    public static String getGetterNameByFieldName(String fieldName) {
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }
}
