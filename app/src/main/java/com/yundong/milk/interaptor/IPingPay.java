package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.PingPayBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/2.
 */

public interface IPingPay {
    Observable<PingPayBean> pingPay(
            String order_no
            , String user_id
            , String channel
    );
}
