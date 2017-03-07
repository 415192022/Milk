package com.yundong.milk.interaptor;

import com.yundong.milk.model.BuyNowBean;
import com.yundong.milk.model.GoodsCommentListBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface IGoodsCommentList {
    Observable<GoodsCommentListBean> goodsCommentList(String goods_id, String page);
}
