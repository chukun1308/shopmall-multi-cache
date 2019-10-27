package com.chukun.cache.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chukun.cache.constant.CommonConstant;
import com.chukun.cache.model.Product;
import com.chukun.cache.model.Shop;
import com.chukun.cache.service.GuavaCacheService;
import com.chukun.cache.service.ProductCacheService;
import com.chukun.cache.service.ShopCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 店铺缓存接口实现
 * @author Administrator
 *
 */
@Service("shopCacheService")
public class ShopCacheServiceImpl implements ShopCacheService {

	@Autowired
	private GuavaCacheService cacheService;


	@Override
	public Shop saveShop2LocalCache(Shop shop) {
		String jsonProduct = JSONObject.toJSONString(shop);
		cacheService.setCommonCache(CommonConstant.LOCAL_CACHE_PREFIX+shop.getId(),jsonProduct);
		return shop;
	}

	@Override
	public Shop getShopFromLocalCache(Long id) {
		Object cacheProduct = cacheService.getCommonCache(CommonConstant.LOCAL_CACHE_PREFIX + id);
		if(Objects.isNull(cacheProduct)){
			return null;
		}
		return JSONObject.parseObject(cacheProduct.toString(),Shop.class);
	}
}
