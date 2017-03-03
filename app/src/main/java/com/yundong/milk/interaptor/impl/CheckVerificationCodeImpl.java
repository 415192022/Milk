package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.ICheckVerificationCodeService;
import com.yundong.milk.api.service.ILoginService;
import com.yundong.milk.interaptor.ICheckVerificationCode;
import com.yundong.milk.interaptor.ILogin;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.CheckVerificationCodeBean;
import com.yundong.milk.model.LoginBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/27.
 */

public class CheckVerificationCodeImpl implements ICheckVerificationCode {

    @Override
    public Observable<BaseReceiveBean> checkVerificationCode(String mobile, String msg) {
        return RetrofitUtils.getInstance().retrofitCtreate(URLConst.URL_MILK_BASE, ICheckVerificationCodeService.class).CheckVerificationCode(mobile, msg);
    }
}
