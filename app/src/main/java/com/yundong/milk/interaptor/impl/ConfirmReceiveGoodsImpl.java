package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IConfirmReceiveGoodsService;
import com.yundong.milk.api.service.IDeleteCarService;
import com.yundong.milk.interaptor.IConfirmReceiveGoods;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/13.
 */

public class ConfirmReceiveGoodsImpl implements IConfirmReceiveGoods {
    @Override
    public Observable<BaseReceiveBean> confirmReceiveGoods(String order_id) {
        return RetrofitUtils.
                getInstance().
                retrofitCtreate(URLConst.URL_MILK_BASE, IConfirmReceiveGoodsService.class).confirmReceiveGoodsS(order_id);
    }
}
