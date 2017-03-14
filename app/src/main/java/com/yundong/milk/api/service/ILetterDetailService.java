package com.yundong.milk.api.service;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.LetterDetailBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public interface ILetterDetailService {
    @FormUrlEncoded
    @POST("goods/article_info")
    Observable<LetterDetailBean> letterDetail(@Field("article_id") String article_id
    );
}
