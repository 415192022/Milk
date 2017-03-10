package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.FindPwdBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface IFindPwd {
    Observable<FindPwdBean> findPwd(
            String mobile,
            String password,
            String msg

    );
}
