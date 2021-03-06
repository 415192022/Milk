package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.GoodsCommentBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/1.
 */

public interface IGoodsCollectionService {
    @FormUrlEncoded
    @POST("goods/goods_collection")
    Observable<BaseReceiveBean> goodsCollection(@Field("user_id") String user_id, @Field("goods_id") String goods_id
    );

}
