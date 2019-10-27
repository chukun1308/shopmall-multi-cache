package com.chukun.cache.service;


import com.chukun.cache.model.Product;
import com.chukun.cache.model.Shop;

/**
 * 店铺缓存接口
 * @author chukun
 *
 */
public interface ShopCacheService {

	/**
	 * 将店铺信息保存到本地缓存中
	 * @param shop
	 * @return
	 */
	Shop saveShop2LocalCache(Shop shop);


	/**
	 * 从本地缓存中获取店铺信息
	 * @param id
	 * @return
	 */
	Shop getShopFromLocalCache(Long id);
	
}
