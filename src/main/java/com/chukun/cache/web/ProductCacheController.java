package com.chukun.cache.web;

import com.chukun.cache.model.Product;
import com.chukun.cache.service.ProductCacheService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author chukun
 */
@RestController("productCacheController")
public class ProductCacheController {

    @Resource
    private ProductCacheService productCacheService;

    @RequestMapping("/testPutCache")
    public String testPutCache(Product product) {
        productCacheService.saveProduct2LocalCache(product);
        return "success";
    }

    @RequestMapping("/testGetCache")
    public Product testGetCache(Long id) {
        return productCacheService.getProductFromLocalCache(id);
    }

}
