package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.LoginBean;

import rx.Observable;


/**
 * Created by MingweiLi on 2017/2/27.
 */

public interface ILogin {
    Observable<LoginBean> login(String mobile, String password);
}
