package com.yundong.milk.view;

import com.yundong.milk.model.LoginBean;
import com.yundong.milk.model.RegistBean;

/**
 * Created by MingweiLi on 2017/2/27.
 */

public interface IRegisterView {
    void register(RegistBean loginBean);
    void registerOnError(String e);
}
