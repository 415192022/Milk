package com.yundong.milk.api.service;

import com.yundong.milk.model.HotSearchBean;
import com.yundong.milk.model.RecommentTypeBean;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public interface IHotSearchService {
    @POST("goods/hot_search")
    Observable<HotSearchBean> getHotSearch();
}
