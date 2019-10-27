package com.chukun.cache.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chukun.cache.constant.CommonConstant;
import com.chukun.cache.model.Product;
import com.chukun.cache.model.Shop;
import com.chukun.cache.redis.RedisMapper;
import com.chukun.cache.service.CacheRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author chukun
 * redis缓存服务
 */
@Service("cacheRedisService")
public class CacheRedisServiceImpl implements CacheRedisService {
    @Autowired
    private RedisMapper redisMapper;

    /**
     * 保存商品的信息到redis缓存
     * @param product
     */
    @Override
    public void saveProduct2RedisCache(Product product) {
        String productJson = JSONObject.toJSONString(product);
        redisMapper.set(CommonConstant.REDIS_PRODUCT_CACHE_PREFIX+product.getId(),productJson);
    }

    /**
     * 保存店铺的信息到redis缓存
     * @param shop
     */
    @Override
    public void saveShop2RedisCache(Shop shop) {
        String shopJson = JSONObject.toJSONString(shop);
        redisMapper.set(CommonConstant.REDIS_SHOP_CACHE_PREFIX+shop.getId(),shopJson);
    }

    @Override
    public void saveRedisSet(String key, String value) {
        redisMapper.setRedisSet(key,value);
    }

    @Override
    public boolean isMember(String key, String value) {
        return redisMapper.hasRedisSetValue(key,value);
    }
}
