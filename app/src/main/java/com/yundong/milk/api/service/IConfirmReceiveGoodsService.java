package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public interface IConfirmReceiveGoodsService {
    @FormUrlEncoded
    @POST("order/confirm_receipt")
    Observable<BaseReceiveBean> confirmReceiveGoodsS(
            @Field("order_id") String order_id
    );

}
