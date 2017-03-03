package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/27.
 */

public interface ICheckVerificationCodeService {
    @FormUrlEncoded
    @POST("check/msg")
    Observable<BaseReceiveBean> CheckVerificationCode(@Field("mobile") String mobile,@Field("msg") String msg
    );
}
