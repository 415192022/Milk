package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.OrderListBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface IOrderList {
    Observable<OrderListBean> modifyNacikName(String user_id, String order_state, String is_comment, String page, String page_data);
}
