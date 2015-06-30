package com.teddy.lc4e.plugins.cache;

import com.teddy.lc4e.core.database.model.SysComVar;
import com.teddy.lc4e.core.database.service.ComVarDao;
import com.teddy.lc4e.core.util.common.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by teddy on 2015/5/21.
 */

@Service
public class CacheHandler {

    @Autowired
    private EhCacheCacheManager springCacheManager;
    @Autowired
    private ComVarDao comVarDao;

    public boolean setCache(String cacheName, Object key, Object value) {
        Assert.notNull(cacheName,"the given name of cache must be not empty.");
        Assert.notNull(key,"the given key must be not empty.");
        Assert.notNull(value,"the given value must be not empty.");
        try {
            Cache cache = springCacheManager.getCache(cacheName);
            if (cache == null) {
                cache = createCacheName(cacheName);
            }
            cache.put(key, value);
            return true;
        } catch (Exception e) {
            e.getCause();
            return false;
        }
    }

    public Object getCache(String cacheName, Object key) {
        Assert.notNull(cacheName,"the given name of cache must be not empty.");
        Assert.notNull(key,"the given key must be not empty.");
        try {
            Cache cache = springCacheManager.getCache(cacheName);
            if (cache == null) {
                return null;
            }
            return cache.get(key).get();
        } catch (Exception e) {
            e.getCause();
            return null;
        }
    }

    public Cache createCacheName(String cacheName) {
        Assert.notNull(cacheName,"the given name of cache must be not empty.");
        try {
            springCacheManager.getCacheManager().addCache(cacheName);
            return springCacheManager.getCache(cacheName);
        } catch (Exception e) {
            e.getCause();
            return null;
        }
    }

    public boolean remove(String cacheName, Object key) {
        Assert.notNull(cacheName,"the given name of cache must be not empty.");
        Assert.notNull(key,"the given key must be not empty.");
        try {
            Cache cache = springCacheManager.getCache(cacheName);
            if (cache == null) {
                return true;
            }
            cache.evict(key);
            return true;
        } catch (Exception e) {
            e.getCause();
            return false;
        }
    }

    public boolean clear(String cacheName) {
        Assert.notNull(cacheName,"the given name of cache must be not empty.");
        try {
            Cache cache = springCacheManager.getCache(cacheName);
            if (cache == null) {
                return true;
            }
            cache.clear();
            return true;
        } catch (Exception e) {
            e.getCause();
            return false;
        }
    }

    public boolean useCache() {
        Object obj = getCache(Global.VAR, Global.Cache);
        if (obj == null) {
            SysComVar var = comVarDao.getSysComVarByName(Global.Cache);
            if (var != null) {
                obj = var.getValue();
            } else {
                var = new SysComVar();
                var.setName(Global.Cache);
                var.setValue(true);
                obj = true;
            }
            setCache(Global.VAR, Global.Cache, var);
        }else{
            obj = ((SysComVar) obj).getValue();
        }
        return (boolean) obj;
    }
}
