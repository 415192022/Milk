package com.yundong.milk.view;

import com.yundong.milk.model.BaseReceiveBean;

/**
 * Created by MingweiLi on 2017/3/13.
 */

public interface IConfirmReceiveGoodsView {
    void confirmReceiveGoods(BaseReceiveBean baseReceiveBean);
    void confirmReceiveGoodsOnError(String e);
}
