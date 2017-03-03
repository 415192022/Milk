package com.yundong.milk.api.service;

import com.yundong.milk.model.RecommentTypeBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public interface IRecommentTypeService {
    @POST("class/rec")
    Observable<RecommentTypeBean> getRecommentType();
}
