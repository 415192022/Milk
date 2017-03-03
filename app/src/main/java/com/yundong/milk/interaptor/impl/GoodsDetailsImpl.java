package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IGoodsDetailsService;
import com.yundong.milk.api.service.IRecommentTypeService;
import com.yundong.milk.interaptor.IGoodsDetails;
import com.yundong.milk.model.GoodsDetailsBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/2.
 */

public class GoodsDetailsImpl implements IGoodsDetails {
    @Override
    public Observable<GoodsDetailsBean> getTypeGoods(String goods_id) {
        return RetrofitUtils.getInstance()
            .retrofitCtreate(URLConst.URL_MILK_BASE, IGoodsDetailsService.class)
            .getGoodsDetails(goods_id);
    }
}
