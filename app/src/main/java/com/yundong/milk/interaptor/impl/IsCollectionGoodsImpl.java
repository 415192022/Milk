package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IIsCollectionGoodsService;
import com.yundong.milk.interaptor.IIsCollectionGoods;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/3.
 */

public class IsCollectionGoodsImpl implements IIsCollectionGoods {
    @Override
    public Observable<BaseReceiveBean> isCollectionGoods(String user_id, String goods_id) {
        return RetrofitUtils.getInstance().retrofitCtreate(URLConst.URL_MILK_BASE, IIsCollectionGoodsService.class).isCollectionGoods(user_id, goods_id);
    }
}
