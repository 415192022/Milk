package com.yundong.milk.view;

import com.yundong.milk.model.RefundBean;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface IRefundView {
    void refundList(RefundBean refundBean);
    void refundListOnError(String e);
}
