package com.yundong.milk.view;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.LoginBean;

/**
 * Created by MingweiLi on 2017/2/27.
 */

public interface ILoginView {
    void login(LoginBean baseReceiveBean);
    void loginOnError(String e);
}
