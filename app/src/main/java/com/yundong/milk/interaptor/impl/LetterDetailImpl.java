package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IIsCollectionGoodsService;
import com.yundong.milk.api.service.ILetterDetailService;
import com.yundong.milk.interaptor.ILetterDetail;
import com.yundong.milk.model.LetterDetailBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/13.
 */

public class LetterDetailImpl implements ILetterDetail {
    @Override
    public Observable<LetterDetailBean> letterDetail(String article_id) {
        return RetrofitUtils
                .getInstance()
                .retrofitCtreate(URLConst.URL_MILK_BASE, ILetterDetailService.class).letterDetail(article_id);
    }
}
