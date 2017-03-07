package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.OrderListBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface IOrderListService {
    @FormUrlEncoded
    @POST("order/order_list")
    Observable<OrderListBean> orderList(
            @Field("user_id") String user_id
            , @Field("order_state") String order_state
            , @Field("is_comment") String is_comment
            , @Field("page") String page
            , @Field("page_data") String page_data);
}
