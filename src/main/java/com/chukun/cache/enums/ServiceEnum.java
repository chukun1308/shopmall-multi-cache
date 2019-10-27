package com.chukun.cache.enums;

public enum  ServiceEnum {

    PRODUCT_MESSAGE_SERVICE("productService","商品服务"),
    SHOP_MESSAGE_SERVICE("shopService","店铺服务");

    private String serviceId;
    private String serviceDesc;

    ServiceEnum(String serviceId,String serviceDesc){
        this.serviceId = serviceId;
        this.serviceDesc = serviceDesc;
    }


    public String getServiceId() {
        return serviceId;
    }


    public String getServiceDesc() {
        return serviceDesc;
    }

}
