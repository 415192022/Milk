package com.yundong.milk.view;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.BaseReceivesBean;
import com.yundong.milk.model.TypeGoodsBean;

/**
 * Created by MingweiLi on 2017/3/1.
 */

public interface ITypeGoodsView {
    void getTypeGoods(TypeGoodsBean typeGoodsBean);

    void getTypeGoodsOnError(String e);
}
