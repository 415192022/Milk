package com.yundong.milk.view;

import com.yundong.milk.model.BuyNowBean;
import com.yundong.milk.model.MyCollectionBean;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface IMyCollectionView {
    void myCollection(MyCollectionBean myCollectionBean);
    void myCollectionOnError(String e);
}
