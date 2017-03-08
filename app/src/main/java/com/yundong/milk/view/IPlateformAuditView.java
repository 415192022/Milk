package com.yundong.milk.view;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IPlatformAuditService;
import com.yundong.milk.interaptor.IPlatformAudit;
import com.yundong.milk.model.PlatformAuditBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/27.
 */

public interface IPlateformAuditView {
    void plateformAudit(PlatformAuditBean platformAuditBean);
    void plateformAuditOnError(String e);
}
