package com.chukun.cache.kafka;

import com.alibaba.fastjson.JSONObject;
import com.chukun.cache.constant.CommonConstant;
import com.chukun.cache.context.SpringContext;
import com.chukun.cache.enums.ServiceEnum;
import com.chukun.cache.model.Product;
import com.chukun.cache.model.Shop;
import com.chukun.cache.service.CacheRedisService;
import com.chukun.cache.service.ProductCacheService;
import com.chukun.cache.service.ShopCacheService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.util.DigestUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author chukun
 *  kafka 消费者
 */
public class CacheKafkaConsumer implements Runnable {

    private String topic;
    private ProductCacheService productCacheService;
    private ShopCacheService shopCacheService;
    private CacheRedisService redisService;

    public CacheKafkaConsumer(String topic){
        this.topic = topic;
        this.productCacheService = SpringContext.getApplicationContext().getBean(ProductCacheService.class);
        this.shopCacheService = SpringContext.getApplicationContext().getBean(ShopCacheService.class);
        this.redisService = SpringContext.getApplicationContext().getBean(CacheRedisService.class);
    }

    @Override
    public void run() {
        KafkaConsumer consumer = createConsumer();
        consumer.subscribe(Collections.singletonList(topic));
        while (true) {
            ConsumerRecords<String,String> records = consumer.poll(1000);
            Iterator<ConsumerRecord<String, String>> iterator = records.iterator();
            while (iterator.hasNext()) {
                ConsumerRecord<String, String> next = iterator.next();
                String message = next.value();
                JSONObject messageJson = JSONObject.parseObject(message);
                // 从这里提取出消息对应的服务的标识
                String serviceId = messageJson.getString("serviceId");

                if (ServiceEnum.PRODUCT_MESSAGE_SERVICE.getServiceId().equals(serviceId)) {
                    // 如果是商品信息服务
                    processProductChangeMessage(messageJson);
                } else if (ServiceEnum.SHOP_MESSAGE_SERVICE.getServiceId().equals(serviceId)) {
                    // 如果是店铺信息服务
                    processShopChangeMessage(messageJson);
                }
            }
        }
    }

    /**
     * 处理商品信息变更的消息
     * @param productMessage
     */
    private void processProductChangeMessage(JSONObject productMessage){
        Long productId = productMessage.getLong("productId");
        //根据productId查询商品的相关信息,这里模拟商品查询数据库
        String productJson = "{\"id\": 1, \"name\": \"iphone7手机\", \"price\": 5599, \"pictures\":\"a.jpg,b.jpg\", \"specification\": \"iphone7的规格\", \"service\": \"iphone7的售后服务\", \"color\": \"红色,白色,黑色\", \"size\": \"5.5\", \"shopId\": 1}";
        Product product = JSONObject.parseObject(productJson,Product.class);
        //缓存信息保存到本地缓存
        String md5Product = new String(DigestUtils.md5Digest(product.toString().getBytes()), StandardCharsets.UTF_8);
        //利用redis过滤重复消息
        if(redisService.isMember(CommonConstant.REDIS_PRODUCT_SET,md5Product)){
            return;
        }
        redisService.saveRedisSet(CommonConstant.REDIS_PRODUCT_SET,md5Product);
        productCacheService.saveProduct2LocalCache(product);
        System.out.println("===================获取刚保存到本地缓存的商品信息：" + productCacheService.getProductFromLocalCache(productId));
        redisService.saveProduct2RedisCache(product);
    }

    /**
     * 处理店铺信息变更的消息
     * @param shopMessage
     */
    private void processShopChangeMessage(JSONObject shopMessage){
        // 提取出店铺id
        Long shopId = shopMessage.getLong("shopId");

        // 调用店铺信息服务的接口
        // 店铺信息服务，一般来说就会去查询数据库，shopId=1的商品信息，然后返回回来
        String shopJson = "{\"id\": 1, \"name\": \"小王的手机店\", \"level\": 5, \"goodCommentRate\":0.99}";
        Shop shop = JSONObject.parseObject(shopJson, Shop.class);
        String md5Shop = new String(DigestUtils.md5Digest(shop.toString().getBytes()), StandardCharsets.UTF_8);
        //利用redis过滤重复消息
        if(redisService.isMember(CommonConstant.REDIS_SHOP_SET,md5Shop)){
            return;
        }
        redisService.saveRedisSet(CommonConstant.REDIS_SHOP_SET,md5Shop);
        shopCacheService.saveShop2LocalCache(shop);
        System.out.println("===================获取刚保存到本地缓存的店铺信息：" + shopCacheService.getShopFromLocalCache(shopId));
        redisService.saveShop2RedisCache(shop);
    }

    private static KafkaConsumer createConsumer(){
        Properties params = new Properties();
        params.put(ConsumerConfig.GROUP_ID_CONFIG, CommonConstant.CACHE_GROUP_ID);
        params.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, CommonConstant.AUTO_COMMIT_INTERVAL_MS);
        params.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,CommonConstant.KAFKA_BOOTSTRAP_SERVERS);
        params.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, CommonConstant.MAX_POLL_INTERVAL_MS);
        params.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, CommonConstant.MAX_POLL_RECORDS);
        params.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        params.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new KafkaConsumer(params);
    }
}
