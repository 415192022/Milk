package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.MyCollectionBean;
import com.yundong.milk.model.RecommentTypeBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public interface IReturnGoodsService {
    @FormUrlEncoded
    @POST("order/return_goods")
    Observable<BaseReceiveBean> returnGoods(
            @Field("user_id") String user_id,
            @Field("order_id") String order_id,
            @Field("return_reason") String return_reason,
            @Field("return_img") String return_img
    );
}
