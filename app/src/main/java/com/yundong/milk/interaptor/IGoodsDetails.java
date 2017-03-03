package com.yundong.milk.interaptor;

import com.yundong.milk.model.GoodsDetailsBean;
import com.yundong.milk.model.TypeGoodsBean;

import rx.Observable;


/**
 * Created by MingweiLi on 2017/2/27.
 */

public interface IGoodsDetails {
    Observable<GoodsDetailsBean> getTypeGoods(String goods_id);
}
