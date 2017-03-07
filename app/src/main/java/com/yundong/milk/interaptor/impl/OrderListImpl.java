package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IOrderListService;
import com.yundong.milk.interaptor.IOrderList;
import com.yundong.milk.model.OrderListBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public class OrderListImpl implements IOrderList {
    @Override
    public Observable<OrderListBean> modifyNacikName(String user_id, String order_state, String is_comment, String page, String page_data) {
        return RetrofitUtils.getInstance()
                .retrofitCtreate(URLConst.URL_MILK_BASE, IOrderListService.class)
                .orderList(user_id, order_state, is_comment, page, page_data);
    }
}
