package com.teddy.lc4e.core.web.service;

import com.teddy.lc4e.core.database.service.ComVarService;
import com.teddy.lc4e.core.util.cache.CacheHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by teddy on 2015/6/14.
 */
@Service
public class ComVariableData {

    @Autowired
    private CacheHandler cacheHandler;

    @Autowired
    private ComVarService comVarService;


    public Object getComVarByName(String name, Object type) {
        Object obj = cacheHandler.getCache("comVar", "UseCache");
        if (obj != null && (Boolean) obj == true) {
            Object value = cacheHandler.getCache("comVar", name);
            if (value != null) {
                return value;
            }
        }
        String value = comVarService.getValueByComVarByName(name).trim();
        Object rvar = null;
        if (type instanceof Integer) {
            if (value.equals("")) {
                rvar = 0;
            } else {
                rvar = Integer.valueOf(value);
            }
        } else if (type instanceof String) {
            rvar = value;
        } else if (type instanceof Double) {
            if (value.equals("")) {
                rvar = 0.0;
            } else {
                rvar = Double.valueOf(value);
            }
        } else if (type instanceof Boolean) {
            if (value.equals("")) {
                rvar = false;
            } else {
                rvar = Boolean.valueOf(value);
            }
        }
        cacheHandler.setCache("comVar", name, rvar);
        return rvar;
    }

}
