package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.BaseReceivesBean;
import com.yundong.milk.model.TypeBrandBean;
import com.yundong.milk.model.TypeGoodsBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/1.
 */

public interface ITypeGoodsService {
    @FormUrlEncoded
    @POST("goods/class_goods")
    Observable<TypeGoodsBean> getTypeGoods(@Field("class_id") String class_id, @Field("brand_id") String brand_id
            , @Field("page") String page, @Field("page_data") String page_data);
}
