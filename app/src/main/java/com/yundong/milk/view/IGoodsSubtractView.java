package com.yundong.milk.view;

import com.yundong.milk.model.BaseReceiveBean;

/**
 * Created by MingweiLi on 2017/3/16.
 */

public interface IGoodsSubtractView {
    void goodsSubtract(BaseReceiveBean baseReceiveBean);
    void goodsSubtractOnError(String e);
}
