package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.GoodsCommentBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/2.
 */

public interface IGoodsCollection {
    Observable<BaseReceiveBean> goodsCollection(String user_id,String goods_id);
}
