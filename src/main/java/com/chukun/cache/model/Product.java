package com.chukun.cache.model;

/**
 * 商品信息
 * @author 初坤
 *
 */
public class Product {

	/**
	 * 商品ID
	 */
	private Long id;
	/**
	 * 商品名称
	 */
	private String name;
	/**
	 * 商品价格
	 */
	private Double price;

	/**
	 * 图片信息
	 */
	private String pictures;
	/**
	 * 规格信息
	 */
	private String specification;
	/**
	 * 售后信息
	 */
	private String service;
	/**
	 * 颜色
	 */
	private String color;
	/**
	 * 尺寸
	 */
	private String size;
	/**
	 * 店铺ID
	 */
	private Long shopId;
	
	public Product() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", name='" + name + '\'' +
				", price=" + price +
				", pictures='" + pictures + '\'' +
				", specification='" + specification + '\'' +
				", service='" + service + '\'' +
				", color='" + color + '\'' +
				", size='" + size + '\'' +
				", shopId=" + shopId +
				'}';
	}
}
