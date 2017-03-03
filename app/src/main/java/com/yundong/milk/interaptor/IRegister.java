package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.LoginBean;
import com.yundong.milk.model.RegistBean;

import rx.Observable;


/**
 * Created by MingweiLi on 2017/2/27.
 */

public interface IRegister {
    Observable<RegistBean> register(String mobile, String password);
}
