package com.yundong.milk.interaptor;

import com.yundong.milk.model.BuyNowBean;
import com.yundong.milk.model.MyCollectionBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface IMyCollection {
    Observable<MyCollectionBean> myCollection(String user_id);
}
