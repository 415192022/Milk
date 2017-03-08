package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.RecommentTypeBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public interface IApplyForModifyService {
    @FormUrlEncoded
    @POST("address_check")
    Observable<BaseReceiveBean> applyForModify(@Field("address_check")String address_check);
}
