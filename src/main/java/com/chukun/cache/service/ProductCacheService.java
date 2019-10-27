package com.chukun.cache.service;


import com.chukun.cache.model.Product;

/**
 * 商品缓存接口
 * @author chukun
 *
 */
public interface ProductCacheService {

	/**
	 * 将商品信息保存到本地缓存中
	 * @param product
	 * @return
	 */
	 Product saveProduct2LocalCache(Product product);


	/**
	 * 从本地缓存中获取商品信息
	 * @param id
	 * @return
	 */
	 Product getProductFromLocalCache(Long id);
	
}
