package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface ICancleOrderService {
    @FormUrlEncoded
    @POST("order/cancel_order")
    Observable<BaseReceiveBean> cancleOrder(
            @Field("order_id") String order_id);
}
