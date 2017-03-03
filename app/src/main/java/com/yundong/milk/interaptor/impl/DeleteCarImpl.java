package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IAddCarService;
import com.yundong.milk.api.service.IDeleteCarService;
import com.yundong.milk.interaptor.IAddCar;
import com.yundong.milk.interaptor.IDeleteCar;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/3.
 */

public class DeleteCarImpl implements IDeleteCar {
    @Override
    public Observable<BaseReceiveBean> deleteCar(String cart_id) {
        return RetrofitUtils.
                getInstance().
                retrofitCtreate(URLConst.URL_MILK_BASE, IDeleteCarService.class).deleteCar(cart_id);
    }
}
