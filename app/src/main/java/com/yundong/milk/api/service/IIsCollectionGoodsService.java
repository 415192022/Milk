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

public interface IIsCollectionGoodsService {
    @FormUrlEncoded
    @POST("goods/is_collection")
    Observable<BaseReceiveBean> isCollectionGoods(@Field("user_id") String user_id,@Field("goods_id") String goods_id);
}
