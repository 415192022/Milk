package com.yundong.milk.api.service;

import com.yundong.milk.model.BuyNowBean;
import com.yundong.milk.model.MyCollectionBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/3.
 */

public interface IMyCollectionService {
    @FormUrlEncoded
    @POST("my_collection")
    Observable<MyCollectionBean> myCollection(@Field("user_id") String user_id
    );
}
