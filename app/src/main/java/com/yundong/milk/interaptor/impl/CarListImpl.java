package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.ICarListService;
import com.yundong.milk.api.service.IGetVerificationService;
import com.yundong.milk.interaptor.ICarList;
import com.yundong.milk.model.CarListBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/3.
 */

public class CarListImpl implements ICarList {
    @Override
    public Observable<CarListBean> getCarList(String user_id) {
        return RetrofitUtils.
                getInstance().
                retrofitCtreate(URLConst.URL_MILK_BASE, ICarListService.class)
                .getCarList(user_id);
    }
}
