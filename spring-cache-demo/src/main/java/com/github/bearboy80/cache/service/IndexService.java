package com.github.bearboy80.cache.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author bearboy80
 * 定义CacheConfig配置，自定设置key
 */
@CacheConfig(cacheNames = "indexService", keyGenerator = "customerKeyGenerator")
@Service
public class IndexService {
    @Cacheable
    public String getIndexNum(String str) {
        return str;
    }
}
