package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.ICheckVerificationCodeService;
import com.yundong.milk.api.service.IPlatformAuditService;
import com.yundong.milk.interaptor.ICheckVerificationCode;
import com.yundong.milk.interaptor.IPlatformAudit;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.PlatformAuditBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/27.
 */

public class PlateformAuditImpl implements IPlatformAudit {


    @Override
    public Observable<PlatformAuditBean> platformAudit(String user_id) {
        return RetrofitUtils.getInstance()
                .retrofitCtreate(URLConst.URL_MILK_BASE, IPlatformAuditService.class)
                .platformAudit(user_id);
    }
}
