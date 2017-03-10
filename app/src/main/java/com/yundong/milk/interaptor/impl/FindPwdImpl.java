package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IFindPwdService;
import com.yundong.milk.api.service.IGetVerificationService;
import com.yundong.milk.interaptor.IFindPwd;
import com.yundong.milk.model.FindPwdBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/10.
 */

public class FindPwdImpl implements IFindPwd {
    @Override
    public Observable<FindPwdBean> findPwd(String mobile, String password, String msg) {
        return RetrofitUtils.
                getInstance().
                retrofitCtreate(URLConst.URL_MILK_BASE, IFindPwdService.class).
                findPwd(mobile,password,msg);
    }
}
