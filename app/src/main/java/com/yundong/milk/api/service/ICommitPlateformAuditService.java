package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public interface ICommitPlateformAuditService {
    @FormUrlEncoded
    @POST("company_edit")
    Observable<BaseReceiveBean> commitPlateformAudit(@Field("user_id") String user_id
            , @Field("company_name") String company_name
            , @Field("area") String area
            , @Field("area_id") String area_id
            , @Field("charge_people") String charge_people
            , @Field("charge_phone") String charge_phone
            , @Field("license") String license
    );
}
