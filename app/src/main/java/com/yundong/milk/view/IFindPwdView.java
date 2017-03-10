package com.yundong.milk.view;

import com.yundong.milk.model.FindPwdBean;

/**
 * Created by MingweiLi on 2017/3/10.
 */

public interface IFindPwdView {
    void findPwd(FindPwdBean findPwdBean);
    void findPwdOnError(String e);
}
