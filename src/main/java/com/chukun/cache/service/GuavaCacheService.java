package com.chukun.cache.service;

/**
 * 利用guava做内存缓存
 */
public interface GuavaCacheService {

    /**
     * 设置本地缓存
     * @param key
     * @param value
     */
    void setCommonCache(String key, Object value);

    /**
     * 获取本地缓存
     * @param key
     * @return
     */
    Object getCommonCache(String key);
}
