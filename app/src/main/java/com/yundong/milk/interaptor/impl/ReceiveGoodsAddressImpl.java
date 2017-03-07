package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IBuyNowService;
import com.yundong.milk.api.service.IReceiveGoodsAddressService;
import com.yundong.milk.interaptor.IBuyNow;
import com.yundong.milk.interaptor.IReceiveGoodsAddress;
import com.yundong.milk.model.BuyNowBean;
import com.yundong.milk.model.ReceiveGoodsAddressBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public class ReceiveGoodsAddressImpl implements IReceiveGoodsAddress {

    @Override
    public Observable<ReceiveGoodsAddressBean> receiveGoodsAddress(String user_id) {
        return RetrofitUtils.getInstance()
                .retrofitCtreate(URLConst.URL_MILK_BASE, IReceiveGoodsAddressService.class)
                .receiveGoodsAddress(user_id);
    }
}
