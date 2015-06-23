package com.teddy.lc4e.core.util.common;

import java.lang.annotation.Annotation;
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

}
