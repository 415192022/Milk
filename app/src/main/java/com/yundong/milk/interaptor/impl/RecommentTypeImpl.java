package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IRecommentTypeService;
import com.yundong.milk.interaptor.IRecommentType;
import com.yundong.milk.model.RecommentTypeBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public class RecommentTypeImpl implements IRecommentType {
    @Override
    public Observable<RecommentTypeBean> getRecommentType() {
        return RetrofitUtils.getInstance()
                .retrofitCtreate(URLConst.URL_MILK_BASE, IRecommentTypeService.class)
                .getRecommentType();
    }
}
