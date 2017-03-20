package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IAddCarService;
import com.yundong.milk.api.service.IPingPayService;
import com.yundong.milk.interaptor.IAddCar;
import com.yundong.milk.interaptor.IPingPay;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.PingPayBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/3.
 */

public class PingPayImpl implements IPingPay {

    @Override
    public Observable<PingPayBean> pingPay(String order_no, String user_id, String channel) {
        return RetrofitUtils.
                getInstance().
                retrofitCtreate(URLConst.URL_MILK_BASE, IPingPayService.class).
                addCar(order_no, user_id, channel);
    }
}
