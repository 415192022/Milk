package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IApplyForChangeCompanyService;
import com.yundong.milk.api.service.IApplyForModifyService;
import com.yundong.milk.interaptor.IApplyForChangeCommany;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/14.
 */

public class ApplyForCompanyImpl implements IApplyForChangeCommany {
    @Override
    public Observable<BaseReceiveBean> applyForChangeCommany(String company_id) {
        return RetrofitUtils.getInstance()
                .retrofitCtreate(URLConst.URL_MILK_BASE, IApplyForChangeCompanyService.class)
                .applyForChangeCompany(company_id);
    }
}
