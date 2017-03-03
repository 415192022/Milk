package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.CarListBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/2.
 */

public interface ICarList {
    Observable<CarListBean> getCarList(String user_id);
}
