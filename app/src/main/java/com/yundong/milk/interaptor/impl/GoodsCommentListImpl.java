package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IGoodsCommentListService;
import com.yundong.milk.interaptor.IGoodsCommentList;
import com.yundong.milk.model.GoodsCommentListBean;
import com.yundong.milk.util.RetrofitUtils;

import retrofit2.Call;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public class GoodsCommentListImpl implements IGoodsCommentList {

    @Override
    public Call<String> goodsCommentList(String goods_id, String page, String scores) {
        return RetrofitUtils.getInstance()
                .retrofitCtreateString(URLConst.URL_MILK_BASE, IGoodsCommentListService.class)
                .goodsCommentList(goods_id, page,scores);
    }
}
