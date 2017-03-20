package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IGoodsDetailsService;
import com.yundong.milk.api.service.IGoodsSubtractService;
import com.yundong.milk.interaptor.IGoodsSubtract;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/16.
 */

public class GoodsSubtractImpl implements IGoodsSubtract {
    @Override
    public Observable<BaseReceiveBean> goodsSubtract(String goods_id, String type, String number) {
        return RetrofitUtils.getInstance()
                .retrofitCtreate(URLConst.URL_MILK_BASE, IGoodsSubtractService.class)
                .goodsSubtract(goods_id,type,number);
    }
}
