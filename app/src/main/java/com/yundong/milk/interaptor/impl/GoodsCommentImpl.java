package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IGoodsCommentService;
import com.yundong.milk.api.service.IHotSearchService;
import com.yundong.milk.interaptor.IGoodsComment;
import com.yundong.milk.model.GoodsCommentBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/2.
 */

public class GoodsCommentImpl implements IGoodsComment {
    @Override
    public Observable<GoodsCommentBean> getGoodsComment(String page) {
        return RetrofitUtils.getInstance()
                .retrofitCtreate(URLConst.URL_MILK_BASE, IGoodsCommentService.class)
                .getGoodsComment(page);
    }
}
