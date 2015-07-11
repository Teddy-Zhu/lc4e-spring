package com.teddy.lc4e.plugins.tools;

import com.alibaba.fastjson.JSONObject;
import com.teddy.lc4e.core.entity.webui.Message;
import com.teddy.lc4e.global.Global;
import org.springframework.ui.Model;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

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

    public static <T, S> S Mapper(T source, Class<S> target) {
        Set<String> sourceFields = new HashSet<String>(), targetFields = new HashSet<String>(), resultFields = new HashSet<String>();
        for (Class<?> clazz = source.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0, len = fields.length; i < len; i++) {
                sourceFields.add(fields[i].getName());
            }
        }
        for (Class<?> clazz = target.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0, len = fields.length; i < len; i++) {
                targetFields.add(fields[i].getName());
            }
        }
        S result = null;
        try {
            result = (S) target.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        resultFields.addAll(sourceFields);
        resultFields.retainAll(targetFields);


        for (String name : resultFields) {
            Method sourceGet = null, targetSet = null;
            try {
                PropertyDescriptor sourcePd = new PropertyDescriptor(name, source.getClass());
                PropertyDescriptor targetPd = new PropertyDescriptor(name, target.getClass());
                sourceGet = sourcePd.getReadMethod();
                targetSet = targetPd.getWriteMethod();
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
            if (sourceGet != null && targetSet != null) {
                try {
                    Object value = sourceGet.invoke(source, new Object[]{});
                    targetSet.invoke(result, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }


    public static String parseDefaultValue(String value) {
        return Global.DEFAULT_NONE.equals(value) ? null : value;

    }
}
