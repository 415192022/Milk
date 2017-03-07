package com.yundong.milk.api.service;

import com.yundong.milk.model.BuyNowBean;
import com.yundong.milk.model.GoodsClassCommentBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/3.
 */

public interface IBuyNowService {
    @FormUrlEncoded
    @POST("order/buy_now")
    Observable<BuyNowBean> buyNow(@Field("user_id") String user_id, @Field("goods_id") String goods_id, @Field("number") String number, @Field("message") String message
    );
}
