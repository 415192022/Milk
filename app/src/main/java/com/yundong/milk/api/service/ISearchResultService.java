package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.SearchResultBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public interface ISearchResultService {
    @FormUrlEncoded
    @POST("goods/search")
    Observable<SearchResultBean> searchResult(
            @Field("goods_name") String goods_name
            , @Field("page") String page
    );
}
