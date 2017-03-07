package com.yundong.milk.model;

/**
 * Created by MingweiLi on 2017/3/7.
 */

public class GoodsAndAddressBean {
    private ReceiveGoodsAddressBean receiveGoodsAddressBean;
    private GoodsDetailsBean goodsDetailsBean;
    private String msg;
    private String count;
    private String totlePrice;

    public void setCount(String count) {
        this.count = count;
    }

    public void setTotlePrice(String totlePrice) {
        this.totlePrice = totlePrice;
    }

    public String getCount() {
        return count;
    }

    public String getTotlePrice() {
        return totlePrice;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setReceiveGoodsAddressBean(ReceiveGoodsAddressBean receiveGoodsAddressBean) {
        this.receiveGoodsAddressBean = receiveGoodsAddressBean;
    }

    public void setGoodsDetailsBean(GoodsDetailsBean goodsDetailsBean) {
        this.goodsDetailsBean = goodsDetailsBean;
    }

    public ReceiveGoodsAddressBean getReceiveGoodsAddressBean() {
        return receiveGoodsAddressBean;
    }

    public GoodsDetailsBean getGoodsDetailsBean() {
        return goodsDetailsBean;
    }
}
