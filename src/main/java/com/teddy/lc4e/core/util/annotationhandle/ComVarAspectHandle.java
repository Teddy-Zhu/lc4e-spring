package com.teddy.lc4e.core.util.annotationhandle;

import com.alibaba.fastjson.JSONObject;
import com.teddy.lc4e.core.database.model.SysComVar;
import com.teddy.lc4e.core.database.service.ComVarService;
import com.teddy.lc4e.core.entity.webui.Message;
import com.teddy.lc4e.core.util.annotation.SetComVar;
import com.teddy.lc4e.core.util.cache.CacheHandler;
import com.teddy.lc4e.core.util.commonfuncion.ReflectTool;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by teddy on 2015/6/14.
 */
@Component
@Aspect
public class ComVarAspectHandle {

    @Autowired
    private ComVarService comVarService;
    @Autowired
    private CacheHandler cacheHandler;

    @SuppressWarnings("finally")
    @Around("@annotation(com.teddy.lc4e.core.util.annotation.SetComVar)")
    public Object setComVar(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean flag = false;
        SetComVar an = null;
        Object[] args = null;
        Method method = null;
        Object target ;
        String methodName;
        try {
            methodName = joinPoint.getSignature().getName();
            target = joinPoint.getTarget();
            method = ReflectTool.getMethodByClassAndName(target.getClass(), methodName);
            args = joinPoint.getArgs(); // all parameters
            an = (SetComVar) ReflectTool.getAnnotationByMethod(method, SetComVar.class);
            flag = setValueByFunctionName(an, args);
        } catch (Exception e) {
            flag = false;
        } finally {
            if (flag) {
                return joinPoint.proceed();
            } else {
                return returnHandle(method, args, an.modIndex(), "Set Config Value" + an.comVar().toString() +" failed");
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

    private boolean setValueByFunctionName(SetComVar fields,Object[] objects){
        String[] configs = fields.comVar();
        if (configs.length ==0){
            return false;
        }
        Model model = (Model) objects[fields.modIndex()];
        Object obj = null;
        if (fields.useCache()){
            List<String> fromDB  =new ArrayList<String>();
            for (int i = 0,len =configs.length; i < len; i++) {
                obj = cacheHandler.getCache(fields.cacheName(), configs[i]);
                if (obj != null) {
                    model.addAttribute(configs[i], obj);
                }else{
                    fromDB.add(configs[i]);
                }
            }
            if (fromDB.size()>0){
                List<SysComVar> sysComVars = comVarService.getValuesByIds(fromDB.toArray(new String[fromDB.size()]));
                for (int i = 0,len = sysComVars.size(); i <len ; i++) {
                    String key = sysComVars.get(i).getStrComConfigName(),value= sysComVars.get(i).getStrComConfigValue();
                    cacheHandler.setCache(fields.cacheName(),key,value);
                    model.addAttribute(key,value);
                }
            }
        }else{
            List<SysComVar> sysComVars = comVarService.getValuesByIds(configs);
            for (int i = 0,len = sysComVars.size(); i <len ; i++) {
                String key = sysComVars.get(i).getStrComConfigName(),value= sysComVars.get(i).getStrComConfigValue();
                cacheHandler.setCache(fields.cacheName(),key,value);
                model.addAttribute(key,value);
            }
        }
        return true;
    }

}
