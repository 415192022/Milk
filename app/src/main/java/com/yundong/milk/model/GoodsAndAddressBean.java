package com.yundong.milk.model;

/**
 * Created by MingweiLi on 2017/3/7.
 */

public class GoodsAndAddressBean {
    private ReceiveGoodsAddressBean receiveGoodsAddressBean;
    private CarListBean.CarListDataA carListDataA;
    private OrderListBean.OrderListData.OrderListDataArray orderListDataArray;
    private GoodsDetailsBean goodsDetailsBean;
    private String msg;
    private String count;
    private String totlePrice;

    public void setOrderListDataArray(OrderListBean.OrderListData.OrderListDataArray orderListDataArray) {
        this.orderListDataArray = orderListDataArray;
    }

    public OrderListBean.OrderListData.OrderListDataArray getOrderListDataArray() {
        return orderListDataArray;
    }

    public void setCarListDataA(CarListBean.CarListDataA carListDataA) {
        this.carListDataA = carListDataA;
    }

    public CarListBean.CarListDataA getCarListDataA() {
        return carListDataA;
    }

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
