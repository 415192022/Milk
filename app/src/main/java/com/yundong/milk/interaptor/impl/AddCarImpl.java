package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IAddCarService;
import com.yundong.milk.api.service.IGetVerificationService;
import com.yundong.milk.interaptor.IAddCar;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/3.
 */

public class AddCarImpl implements IAddCar {
    @Override
    public Observable<BaseReceiveBean> addCar(String user_id, String goods_id, String number) {
        return RetrofitUtils.
                getInstance().
                retrofitCtreate(URLConst.URL_MILK_BASE, IAddCarService.class).
                addCar(user_id, goods_id, number);
    }
}
