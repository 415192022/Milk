package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IFeedBackService;
import com.yundong.milk.api.service.IGoodsCollectionService;
import com.yundong.milk.interaptor.IFeedBack;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/7.
 */

public class FeedBackImpl implements IFeedBack {
    @Override
    public Observable<BaseReceiveBean> feedBack(String user_id, String comment, String phone) {
        return RetrofitUtils.
                getInstance().
                retrofitCtreate(URLConst.URL_MILK_BASE, IFeedBackService.class).
                feedBack(user_id, comment, phone);
    }
}
