package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IGetVerificationService;
import com.yundong.milk.api.service.IRegisterService;
import com.yundong.milk.interaptor.IRegister;
import com.yundong.milk.model.LoginBean;
import com.yundong.milk.model.RegistBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;


/**
 * Created by MingweiLi on 2017/2/27.
 */

public class RegisterImpl implements IRegister {

    @Override
    public Observable<RegistBean> register(String mobile, String password) {
        return RetrofitUtils.
                getInstance().
                retrofitCtreate(URLConst.URL_MILK_BASE, IRegisterService.class).register(mobile, password);
    }
}
