package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public interface IGoodsSubtractService {
    @FormUrlEncoded
    @POST("goods/goods_subtract")
    Observable<BaseReceiveBean> goodsSubtract(
            @Field("goods_id") String goods_id
            , @Field("type") String type
            , @Field("number") String number
    );
}
