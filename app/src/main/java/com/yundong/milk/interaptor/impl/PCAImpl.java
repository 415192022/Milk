package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IOrderListService;
import com.yundong.milk.api.service.IPCAService;
import com.yundong.milk.interaptor.IPCA;
import com.yundong.milk.model.PCABean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/10.
 */

public class PCAImpl implements IPCA {
    @Override
    public Observable<PCABean> getPCA(String area_parent_id) {
        return RetrofitUtils.getInstance()
                .retrofitCtreate(URLConst.URL_MILK_BASE, IPCAService.class)
                .getPCA(area_parent_id);
    }
}
