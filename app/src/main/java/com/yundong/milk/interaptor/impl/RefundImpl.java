package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IRecommentTypeService;
import com.yundong.milk.api.service.IRefundService;
import com.yundong.milk.interaptor.IRefund;
import com.yundong.milk.model.RefundBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public class RefundImpl implements IRefund {
    @Override
    public Observable<RefundBean> refundList(String user_id, String page, String page_data) {
        return RetrofitUtils.getInstance()
                .retrofitCtreate(URLConst.URL_MILK_BASE, IRefundService.class)
                .refundList(user_id, page, page_data);
    }
}
