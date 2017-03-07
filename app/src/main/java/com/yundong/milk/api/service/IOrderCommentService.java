package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.BuyNowBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/3.
 */

public interface IOrderCommentService {
    @FormUrlEncoded
    @POST("order/order_comment")
    Observable<BaseReceiveBean> orderComment(@Field("user_id") String user_id
            , @Field("order_id") String order_id
            , @Field("comment_content") String comment_content
            , @Field("comment_image") String comment_image
            , @Field("fraction") String fraction
    );
}
