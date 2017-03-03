package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.BaseReceivesBean;
import com.yundong.milk.model.TypeBrandBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/1.
 */

public interface ITypeBrandService {

    @FormUrlEncoded
    @POST("class/brand_list")
    Observable<TypeBrandBean> getTypeBrand(@Field("class_id") String class_id
    );
}
