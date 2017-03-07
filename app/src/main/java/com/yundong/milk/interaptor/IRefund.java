package com.yundong.milk.interaptor;

import com.yundong.milk.model.RefundBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface IRefund {
    Observable<RefundBean> refundList(String user_id, String page, String page_data);
}
