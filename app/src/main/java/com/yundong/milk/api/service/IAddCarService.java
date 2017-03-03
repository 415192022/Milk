package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public interface IAddCarService {
    @FormUrlEncoded
    @POST("goods/add_cart")
    Observable<BaseReceiveBean> addCar(@Field("user_id") String user_id, @Field("goods_id") String goods_id, @Field("number") String number);
}
