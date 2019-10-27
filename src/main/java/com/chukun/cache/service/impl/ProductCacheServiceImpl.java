package com.chukun.cache.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chukun.cache.constant.CommonConstant;
import com.chukun.cache.model.Product;
import com.chukun.cache.service.GuavaCacheService;
import com.chukun.cache.service.ProductCacheService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 商品缓存接口实现
 * @author Administrator
 *
 */
@Service("productCacheService")
public class ProductCacheServiceImpl implements ProductCacheService {

	@Autowired
	private GuavaCacheService cacheService;


	@Override
	public Product saveProduct2LocalCache(Product product) {
		String jsonProduct = JSONObject.toJSONString(product);
		cacheService.setCommonCache(CommonConstant.LOCAL_CACHE_PREFIX+product.getId(),jsonProduct);
		return product;
	}

	@Override
	public Product getProductFromLocalCache(Long id) {
		Object cacheProduct = cacheService.getCommonCache(CommonConstant.LOCAL_CACHE_PREFIX + id);
		if(Objects.isNull(cacheProduct)){
			return null;
		}
		return JSONObject.parseObject(cacheProduct.toString(),Product.class);
	}
}
