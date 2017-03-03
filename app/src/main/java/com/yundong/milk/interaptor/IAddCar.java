package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/2.
 */

public interface IAddCar {
    Observable<BaseReceiveBean> addCar(String user_id, String goods_id,String number);
}
