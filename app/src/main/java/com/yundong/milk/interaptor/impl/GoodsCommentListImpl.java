package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IGoodsCommentListService;
import com.yundong.milk.interaptor.IGoodsCommentList;
import com.yundong.milk.model.GoodsCommentListBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public class GoodsCommentListImpl implements IGoodsCommentList {

    @Override
    public Observable<GoodsCommentListBean> goodsCommentList(String goods_id, String page,String scores) {
        return RetrofitUtils.getInstance()
                .retrofitCtreate(URLConst.URL_MILK_BASE, IGoodsCommentListService.class)
                .goodsCommentList(goods_id, page,scores);
    }
}
