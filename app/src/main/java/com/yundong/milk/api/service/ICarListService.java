package com.yundong.milk.api.service;

import com.yundong.milk.model.CarListBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/3.
 */

public interface ICarListService {
    @FormUrlEncoded
    @POST("goods/cart_list")
    Observable<CarListBean> getCarList(@Field("user_id") String user_id
    );
}
