package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IGetVerificationService;
import com.yundong.milk.api.service.IGoodsCollectionService;
import com.yundong.milk.interaptor.IGoodsCollection;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/27.
 */

public class GoodsCollectionImpl implements IGoodsCollection {
    @Override
    public Observable<BaseReceiveBean> goodsCollection(String user_id, String goods_id) {
        return RetrofitUtils.
                getInstance().
                retrofitCtreate(URLConst.URL_MILK_BASE, IGoodsCollectionService.class).
                goodsCollection(user_id, goods_id);
    }
}
