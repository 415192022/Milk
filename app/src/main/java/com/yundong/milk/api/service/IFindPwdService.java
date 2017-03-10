package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.FindPwdBean;
import com.yundong.milk.model.RecommentTypeBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public interface IFindPwdService {
    @FormUrlEncoded
    @POST("find/pwd")
    Observable<FindPwdBean> findPwd(
            @Field("mobile") String mobile
            , @Field("password") String password
            , @Field("msg") String msg
    );
}
