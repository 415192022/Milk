package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.PingPayBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public interface IPingPayService {
    @FormUrlEncoded
    @POST("ping_pay")
    Observable<PingPayBean> addCar(
            @Field("order_no") String order_no
            , @Field("user_id") String user_id
            , @Field("channel") String channel
    );
}
