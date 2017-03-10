package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.MessageInfoBean;
import com.yundong.milk.model.MessageListBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public interface IMessageInfoService {
    @FormUrlEncoded
    @POST("message_info")
    Observable<MessageInfoBean> messageInfo(@Field("message_id") String message_id, @Field("user_id") String user_id
    );
}
