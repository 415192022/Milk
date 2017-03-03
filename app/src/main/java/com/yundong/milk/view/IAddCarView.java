package com.yundong.milk.view;

import com.yundong.milk.model.BaseReceiveBean;

/**
 * Created by MingweiLi on 2017/3/3.
 */

public interface IAddCarView {
    void addCar(BaseReceiveBean baseReceiveBean);
    void addCarOnError(String e);
}
