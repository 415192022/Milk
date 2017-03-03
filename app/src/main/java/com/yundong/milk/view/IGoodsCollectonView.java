package com.yundong.milk.view;

import com.yundong.milk.model.BaseReceiveBean;

/**
 * Created by MingweiLi on 2017/3/3.
 */

public interface IGoodsCollectonView {
    void collection(BaseReceiveBean baseReceiveBean);
    void collectionOnError(String e);
}
