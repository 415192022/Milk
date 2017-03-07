package com.yundong.milk.model;

/**
 * Created by MingweiLi on 2017/3/7.
 * 商品信息和数量的二次封装
 */

public class GoodsAndCountBean {
    private String count;
    private String totlePrice;
    private GoodsDetailsBean goodsDetailsBean;

    @Override
    public String toString() {
        return "GoodsAndCountBean{" +
                "count='" + count + '\'' +
                ", totlePrice='" + totlePrice + '\'' +
                ", goodsDetailsBean=" + goodsDetailsBean +
                '}';
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setTotlePrice(String totlePrice) {
        this.totlePrice = totlePrice;
    }

    public void setGoodsDetailsBean(GoodsDetailsBean goodsDetailsBean) {
        this.goodsDetailsBean = goodsDetailsBean;
    }

    public String getCount() {
        return count;
    }

    public String getTotlePrice() {
        return totlePrice;
    }

    public GoodsDetailsBean getGoodsDetailsBean() {
        return goodsDetailsBean;
    }
}
