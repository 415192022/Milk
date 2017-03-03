package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/2.
 */

public interface IDeleteCar {
    Observable<BaseReceiveBean> deleteCar(String cart_id);
}
