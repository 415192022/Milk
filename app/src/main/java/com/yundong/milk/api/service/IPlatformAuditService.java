package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.PlatformAuditBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public interface IPlatformAuditService {
    @FormUrlEncoded
    @POST("company_info")
    Observable<PlatformAuditBean> platformAudit(@Field("user_id") String user_id);
}
