package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/2.
 */

public interface IGoodsSubtract {
    Observable<BaseReceiveBean> goodsSubtract(String goods_id, String type, String number);
}
