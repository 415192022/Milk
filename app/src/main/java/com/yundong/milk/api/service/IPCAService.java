package com.yundong.milk.api.service;

import com.yundong.milk.model.BuyNowBean;
import com.yundong.milk.model.PCABean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/3.
 */

public interface IPCAService {
    @FormUrlEncoded
    @POST("area/area_list")
    Observable<PCABean> getPCA(
            @Field("area_parent_id") String area_parent_id
    );
}
