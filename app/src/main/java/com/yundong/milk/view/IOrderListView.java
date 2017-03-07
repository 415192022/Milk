package com.yundong.milk.view;

import com.yundong.milk.model.OrderListBean;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface IOrderListView {
    void orderList(OrderListBean orderListBean);
    void  orderListOnError(String e);
}
