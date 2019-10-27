package com.chukun.cache.service;

import com.chukun.cache.model.Product;
import com.chukun.cache.model.Shop;

public interface CacheRedisService {

    /**
     * 保存商品的信息到redis
     * @param product
     */
    void saveProduct2RedisCache(Product product);

    /**
     * 保存店铺的信息到redis
     * @param shop
     */
    void saveShop2RedisCache(Shop shop);

    /**
     * 利用redis去除重复消息
     * @param key
     * @param value
     */
    void saveRedisSet(String key,String value);
    /**
     * 判断redis集合中是否有元素
     * @param key
     * @param value
     */
    boolean isMember(String key,String value);


}
