package com.yundong.milk.api.service;

import com.yundong.milk.model.GoodsDetailsBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public interface IGoodsDetailsService {
    @FormUrlEncoded
    @POST("goods/goods_info")
    Observable<GoodsDetailsBean> getGoodsDetails(@Field("goods_id") String goods_id);
}
