package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public interface IFeedBackService {
    @FormUrlEncoded
    @POST("feedback")
    Observable<BaseReceiveBean> feedBack(@Field("user_id") String user_id, @Field("comment") String comment, @Field("phone") String phone);
}
