package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IGetVerificationService;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/27.
 */

public class GetVerificationImpl implements IGetVerificationService {
    @Override
    public Observable<BaseReceiveBean> getVerificationCode(String mobile) {
        return RetrofitUtils.
                getInstance().
                retrofitCtreate(URLConst.URL_MILK_BASE, IGetVerificationService.class).
                getVerificationCode(mobile);
    }
}
