package com.yundong.milk.view;

import com.yundong.milk.model.PingPayBean;

/**
 * Created by MingweiLi on 2017/3/16.
 */

public interface IPingPayView {
    void pingPay(PingPayBean baseReceiveBean);
    void pingPayOnError(String e);
}
