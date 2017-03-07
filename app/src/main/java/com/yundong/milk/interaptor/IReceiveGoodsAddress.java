package com.yundong.milk.interaptor;

import com.yundong.milk.model.BuyNowBean;
import com.yundong.milk.model.ReceiveGoodsAddressBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface IReceiveGoodsAddress {
    Observable<ReceiveGoodsAddressBean> receiveGoodsAddress(String user_id);
}
