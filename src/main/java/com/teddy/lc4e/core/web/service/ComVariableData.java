package com.teddy.lc4e.core.web.service;

import com.teddy.lc4e.core.database.model.SysComVar;
import com.teddy.lc4e.core.database.service.ComVarDao;
import com.teddy.lc4e.plugins.cache.CacheHandler;
import com.teddy.lc4e.global.Global;
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
    private ComVarDao comVarDao;

    private boolean useCache;

    public Object getComVarByName(String name) {
        useCache = cacheHandler.useCache();

        if (useCache) {
            Object value = cacheHandler.getCache(Global.VAR, name);
            if (value != null) {
                return ((SysComVar) value).getValue();
            }
        }
        Object value = comVarDao.getSysComVarByName(name);
        if (useCache) {
            cacheHandler.setCache(Global.VAR, name, value);
        }
        return ((SysComVar) value).getValue();
    }

}
