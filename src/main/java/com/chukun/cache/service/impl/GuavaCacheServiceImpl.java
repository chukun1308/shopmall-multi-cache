package com.chukun.cache.service.impl;

import com.chukun.cache.service.GuavaCacheService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * guava缓存的实现类 用于热点数据
 * 用于存放脏读不敏感的数据，并且不轻易改变的数据
 */
@Service("guavaCacheService")
public class GuavaCacheServiceImpl implements GuavaCacheService {

    private Cache commonCache;

    @PostConstruct
    private void init(){
        commonCache = CacheBuilder.newBuilder()
                .initialCapacity(1000)  //设置初始容量
                .maximumSize(1500)  //设置最大的容量
                .expireAfterWrite(60, TimeUnit.SECONDS)//设置失效测试为写后60s失效
                //.expireAfterAccess(60,TimeUnit.SECONDS)//也可以设置访问后60s失效
                .build();
    }

    @Override
    public void setCommonCache(String key, Object value) {
          commonCache.put(key,value);
    }

    @Override
    public Object getCommonCache(String key) {
        return commonCache.getIfPresent(key);
    }
}
