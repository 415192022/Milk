package com.yundong.milk.view;

import com.yundong.milk.model.ReceiveGoodsAddressBean;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface IReceiveGoodsAddressView {
    void receiveGoodsAddress(ReceiveGoodsAddressBean receiveGoodsAddressBean);

    void receiveGoodsAddressOnError(String e);
}
