package com.yundong.milk.view;

import com.yundong.milk.model.BuyNowBean;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface IBuyNowView {
    void buyNow(BuyNowBean buyNowBean);
    void buyNowOnError(String e);
}
