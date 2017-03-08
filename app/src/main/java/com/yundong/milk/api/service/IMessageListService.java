package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.MessageListBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public interface IMessageListService {
    @FormUrlEncoded
    @POST("message_list")
    Observable<MessageListBean> messageList(
            @Field("user_id") String user_id
            , @Field("message_type") String message_type
            , @Field("page") String page
            , @Field("page_data") String page_data
    );
}
