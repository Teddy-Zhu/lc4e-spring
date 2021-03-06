package com.teddy.lc4e.core.web.service;

import com.teddy.lc4e.plugins.cache.CacheHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by teddy on 2015/5/21.
 */
@Service
public class WebCacheManager {

    @Autowired
    private CacheHandler cacheHandler;


    public boolean clearCacheByCacheNameAndKey(String cacheName, Object key) {
        return cacheHandler.remove(cacheName, key);
    }

    public boolean clearCacheByCacheName(String cacheName) {
        return cacheHandler.clear(cacheName);
    }

    public boolean setCacheByCacheName(String cacheName, Object key, Object value) {
        return cacheHandler.setCache(cacheName, key, value);
    }

}
