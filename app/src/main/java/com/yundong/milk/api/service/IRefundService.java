package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.RefundBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public interface IRefundService {
    @FormUrlEncoded
    @POST("order/returngoods_list")
    Observable<RefundBean> refundList(@Field("user_id") String user_id, @Field("page") String page, @Field("page_data") String page_data);
}
