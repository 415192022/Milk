package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.LoginBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/27.
 */

public interface IGetVerificationService {
    @FormUrlEncoded
    @POST("send/msg")
    Observable<BaseReceiveBean> getVerificationCode(@Field("mobile") String mobile
    );
}
