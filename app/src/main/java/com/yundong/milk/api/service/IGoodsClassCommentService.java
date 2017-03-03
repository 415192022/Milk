package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.GoodsClassCommentBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/3.
 */

public interface IGoodsClassCommentService {
    @FormUrlEncoded
    @POST("goods/getClassGoods")
    Observable<GoodsClassCommentBean> getGoodsClassComment(@Field("goods_type") String user_id, @Field("page_data") String goods_id, @Field("page") String page
    );
}
