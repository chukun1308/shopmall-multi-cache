package com.chukun.cache.constant;

public class CommonConstant {

    /**
     * 本地缓存的key前缀
     */
    public final static String LOCAL_CACHE_PREFIX="local_cache_";

    public final static String REDIS_PRODUCT_SET = "redis_product_set";


    public final static String REDIS_SHOP_SET = "redis_shop_set";


    /**
     * redis商品缓存前缀
     */
    public final static String REDIS_PRODUCT_CACHE_PREFIX="product_";

    /**
     * redis店铺缓存前缀
     */
    public final static String REDIS_SHOP_CACHE_PREFIX="shop_";


    /**
     * kafka bootStrap 地址
     */
    public final static String KAFKA_BOOTSTRAP_SERVERS = "linux01:9092,linux02:9092,linux03:9092";

    /**
     * kafka组名
     */
    public final static String CACHE_GROUP_ID = "shopmall_cache_group";


    /**
     * kafka自动提交间隔 ms
     */
    public final static int AUTO_COMMIT_INTERVAL_MS = 1000;

    public final static int SESSION_TIMEOUT_MS = 2000;

    /**
     * topic主题
     */
    public final static String MESSAGE_CACHE_TOPIC = "message_cache_topic";

    public final static int MAX_POLL_INTERVAL_MS =1000;

    public final static int MAX_POLL_RECORDS =1;

}
