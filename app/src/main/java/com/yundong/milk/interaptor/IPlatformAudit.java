package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.PlatformAuditBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface IPlatformAudit {
    Observable<PlatformAuditBean> platformAudit(String user_id);
}
