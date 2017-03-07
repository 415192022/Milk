package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.ICancleOrderService;
import com.yundong.milk.interaptor.ICancleOrder;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public class CancleOrderImpl implements ICancleOrder {
    @Override
    public Observable<BaseReceiveBean> cancleOrder(String order_id) {
        return RetrofitUtils.
                getInstance().
                retrofitCtreate(URLConst.URL_MILK_BASE, ICancleOrderService.class).
                cancleOrder(order_id);
    }
}
