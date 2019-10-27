package com.chukun.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisMapper {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * set cache
     * @param key
     * @param value
     */
    public void set(String key, String value){
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set(key,value);
    }

    /**
     * 获取key
     * @param key
     * @return
     */
    public Object get(String key) {
        ValueOperations ops = redisTemplate.opsForValue();
        return ops.get(key);
    }

    /**
     * 删除对应的key
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 判断是否有此key
     * @param key
     * @return
     */
    public boolean existKey(String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 保存至redis的set集合
     * @param key
     * @param value
     */
    public void setRedisSet(String key,String value){
        redisTemplate.opsForSet().add(key,value);
    }

    /**
     * 判断redis集合中是否含有此元素
     * @param key
     * @param value
     */
    public boolean hasRedisSetValue(String key,String value){
        return redisTemplate.opsForSet().isMember(key,value);
    }
}
