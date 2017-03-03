package com.yundong.milk.view;

import com.yundong.milk.model.BaseReceiveBean;

/**
 * Created by MingweiLi on 2017/2/27.
 */

public interface IIsCollectionGoodsView {
    void isCollectionGoods(BaseReceiveBean baseReceiveBean);
    void isCollectionGoodsOnError(String e);
}
