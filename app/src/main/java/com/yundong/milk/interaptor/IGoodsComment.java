package com.yundong.milk.interaptor;

import com.yundong.milk.model.GoodsCommentBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/2.
 */

public interface IGoodsComment {
    Observable<GoodsCommentBean> getGoodsComment(String page);
}
