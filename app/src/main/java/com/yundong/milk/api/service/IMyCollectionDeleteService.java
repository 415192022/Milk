package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.BuyNowBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/3.
 */

public interface IMyCollectionDeleteService {
    @FormUrlEncoded
    @POST("collection_del")
    Observable<BaseReceiveBean> deleteMyCollection(@Field("coll_id") String coll_id);
}
