package com.chukun.cache.model;

public class Shop {
    /**
     * 店铺ID
     */
    private Long id;
    /**
     * 店铺名称
     */
    private String name;
    /**
     * 店铺等级
     */
    private Integer level;
    /**
     * 店铺评分
     */
    private Double goodCommentRate;


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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Double getGoodCommentRate() {
        return goodCommentRate;
    }

    public void setGoodCommentRate(Double goodCommentRate) {
        this.goodCommentRate = goodCommentRate;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", goodCommentRate=" + goodCommentRate +
                '}';
    }
}
