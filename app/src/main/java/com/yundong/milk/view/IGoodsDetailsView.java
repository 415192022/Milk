package com.yundong.milk.view;

import com.yundong.milk.model.GoodsDetailsBean;

/**
 * Created by MingweiLi on 2017/3/2.
 */

public interface IGoodsDetailsView {
    void getGoodsDetails(GoodsDetailsBean goodsDetailsBean);
    void getGoodsDetailsOnError(String e);
}
