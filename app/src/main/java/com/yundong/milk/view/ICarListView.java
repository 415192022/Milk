package com.yundong.milk.view;

import com.yundong.milk.model.CarListBean;
import com.yundong.milk.model.RecommentTypeBean;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public interface ICarListView {
    void getCarList(CarListBean carListBean);
    void getCarListOnError(String e);
}
