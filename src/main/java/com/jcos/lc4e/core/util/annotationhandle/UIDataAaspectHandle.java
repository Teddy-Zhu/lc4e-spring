package com.jcos.lc4e.core.util.annotationhandle;

import com.alibaba.fastjson.JSONObject;
import com.jcos.lc4e.core.entity.Message;
import com.jcos.lc4e.core.util.annotation.SetUIData;
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

    @SuppressWarnings("finally")
    @Around("@annotation(com.jcos.lc4e.core.util.annotation.SetUIData)")
    public Object setUIDataForAn(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean flag = false;
        SetUIData an = null;
        Object[] args = null;
        Method method = null;
        Object target = null;
        String methodName = null;
        try {
            methodName = joinPoint.getSignature().getName();
            target = joinPoint.getTarget();
            method = getMethodByClassAndName(target.getClass(), methodName);
            args = joinPoint.getArgs(); // all parameters
            an = (SetUIData) getAnnotationByMethod(method, SetUIData.class);
            flag = setValueByFunctionName(an, args);
        } catch (Exception e) {
            flag = false;
        } finally {
            if (flag) {
                return joinPoint.proceed();
            } else {
                return returnHandle(method,args,an.modIndex(),"Set UI Data Failed");
            }
        }
    }

    private Object returnHandle(Method method,Object[] args,Integer modelIndex,String failed){
        if (method.getReturnType().getName().equals("java.lang.String")) {
            Model model = (Model) args[modelIndex];
            model.addAttribute("Message", JSONObject.toJSONString(new Message(failed)));
            return "System/Message";
        } else {
            return new Message(failed);
        }
    }

    private boolean setValueByFunctionName(SetUIData setUIData, Object[] args) {
        Model model = (Model) args[setUIData.modIndex()];
        Class clazz = uiData.getClass();

        String[] functions = setUIData.funcName();
        String[] varNames = setUIData.varName();
        int[] useVars = setUIData.useVarIndex();
        Object[] objs = new Object[]{};
        if (useVars.length != 0) {
            objs = new Object[useVars.length];
            for (int i = 0,len=useVars.length; i <len ; i++) {
                objs[i] =  args[useVars[i]];
            }
        }

        if (functions.length != varNames.length) {
            return false;
        }
        if (functions.length != 0) {
            for (int i = 0, len = functions.length; i < len; i++) {
                Method method = null;
                try {
                    method = clazz.getDeclaredMethod(functions[i]);
                    Object obj = method.invoke(uiData,objs);
                    model.addAttribute(varNames[i], obj);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
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
