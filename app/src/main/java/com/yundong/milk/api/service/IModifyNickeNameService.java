package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface IModifyNickeNameService {
    @FormUrlEncoded
    @POST("editUsername")
    Observable<BaseReceiveBean> modifyNickeName(@Field("name") String goods_id, @Field("user_id") String user_id);
}
