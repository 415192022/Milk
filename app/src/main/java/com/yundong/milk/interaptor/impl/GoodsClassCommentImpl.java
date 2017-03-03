package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IGoodsClassCommentService;
import com.yundong.milk.api.service.IGoodsCollectionService;
import com.yundong.milk.interaptor.IGoodsClassComment;
import com.yundong.milk.model.GoodsClassCommentBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/3.
 */

public class GoodsClassCommentImpl implements IGoodsClassComment {

    @Override
    public Observable<GoodsClassCommentBean> getGoodsClassComment(String goods_type, String page_data, String page) {
        return RetrofitUtils.
                getInstance().
                retrofitCtreate(URLConst.URL_MILK_BASE, IGoodsClassCommentService.class).getGoodsClassComment(goods_type, page_data, page);
    }
}
