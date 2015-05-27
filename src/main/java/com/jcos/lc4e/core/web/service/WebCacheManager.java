package com.jcos.lc4e.core.web.service;

import com.jcos.lc4e.core.util.cache.CacheHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by teddy on 2015/5/21.
 */
@Service
public class WebCacheManager {

    @Autowired
    private CacheHandler cacheHandler;


    public boolean clearCacheByKey(String cacheName, Object key) {
        return cacheHandler.remove(cacheName, key);
    }
}
