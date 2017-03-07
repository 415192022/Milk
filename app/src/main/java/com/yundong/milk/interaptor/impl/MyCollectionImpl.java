package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IBuyNowService;
import com.yundong.milk.api.service.IMyCollectionService;
import com.yundong.milk.interaptor.IBuyNow;
import com.yundong.milk.interaptor.IMyCollection;
import com.yundong.milk.model.BuyNowBean;
import com.yundong.milk.model.MyCollectionBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public class MyCollectionImpl implements IMyCollection {

    @Override
    public Observable<MyCollectionBean> myCollection(String user_id) {
        return RetrofitUtils.getInstance()
                .retrofitCtreate(URLConst.URL_MILK_BASE, IMyCollectionService.class)
                .myCollection(user_id);
    }
}
