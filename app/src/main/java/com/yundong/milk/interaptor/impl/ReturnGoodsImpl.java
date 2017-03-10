package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IRefundService;
import com.yundong.milk.api.service.IReturnGoodsService;
import com.yundong.milk.interaptor.IReturnGoods;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/9.
 */

public class ReturnGoodsImpl implements IReturnGoods {
    @Override
    public Observable<BaseReceiveBean> returnGoods(String user_id, String order_id, String return_reason, String return_img) {
        return RetrofitUtils.getInstance()
                .retrofitCtreate(URLConst.URL_MILK_BASE, IReturnGoodsService.class)
                .returnGoods(user_id, order_id, return_reason,return_img);
    }
}
