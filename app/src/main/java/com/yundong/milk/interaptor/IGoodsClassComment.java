package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.GoodsClassCommentBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/3.
 */

public interface IGoodsClassComment {
    Observable<GoodsClassCommentBean> getGoodsClassComment(String goods_type, String page_data, String page);
}
