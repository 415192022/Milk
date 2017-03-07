package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IAllTypeService;
import com.yundong.milk.api.service.IBuyNowService;
import com.yundong.milk.interaptor.IBuyNow;
import com.yundong.milk.model.BuyNowBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public class BuyNowImpl implements IBuyNow {
    @Override
    public Observable<BuyNowBean> buyNow(String user_id, String goods_id, String number, String message) {
        return RetrofitUtils.getInstance()
                .retrofitCtreate(URLConst.URL_MILK_BASE, IBuyNowService.class)
                .buyNow(user_id, goods_id, number, message);
    }
}
