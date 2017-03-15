package com.yundong.milk.interaptor;

import com.yundong.milk.model.GoodsCommentListBean;

import retrofit2.Call;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface IGoodsCommentList {
    Call<String> goodsCommentList(String goods_id, String page, String scores);
}
