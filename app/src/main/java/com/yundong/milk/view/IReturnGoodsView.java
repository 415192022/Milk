package com.yundong.milk.view;

import com.yundong.milk.model.BaseReceiveBean;

/**
 * Created by MingweiLi on 2017/3/9.
 */

public interface IReturnGoodsView {
    void returnGoods(BaseReceiveBean baseReceiveBean);
    void returnGoodsOnError(String e);
}
