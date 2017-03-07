package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.RefundBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface ICancleOrder {
    Observable<BaseReceiveBean> cancleOrder(String order_id);
}
