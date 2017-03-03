package com.yundong.milk.interaptor;

import com.yundong.milk.model.LoginBean;

import rx.Observable;


/**
 * Created by MingweiLi on 2017/2/27.
 */

public interface IGetVerification {
    Observable<LoginBean> login(String mobile);
}
