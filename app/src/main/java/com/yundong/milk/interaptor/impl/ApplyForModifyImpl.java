package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IApplyForModifyService;
import com.yundong.milk.interaptor.IApplyForModify;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public class ApplyForModifyImpl implements IApplyForModify {

    @Override
    public Observable<BaseReceiveBean> applyFroModify(String address_id) {
        return RetrofitUtils.getInstance()
                .retrofitCtreate(URLConst.URL_MILK_BASE, IApplyForModifyService.class)
                .applyForModify(address_id);
    }
}
