package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.BuyNowBean;
import com.yundong.milk.model.RecommentTypeBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public interface IAddReceiveAddressService {
    @FormUrlEncoded
    @POST("setAddress")
    Observable<BaseReceiveBean> addReceiveAddress(
            @Field("user_id") String user_id
            , @Field("phone") String phone
            , @Field("uname") String uname
            , @Field("province_name") String province_name
            , @Field("province_id") String province_id
            , @Field("city_name") String city_name
            , @Field("city_id") String city_id
            , @Field("area_name") String area_name
            , @Field("area_id") String area_id
            , @Field("area_info") String area_info
    );
}
