package com.yundong.milk.view;

import com.yundong.milk.model.BuyNowBean;
import com.yundong.milk.model.GoodsCommentListBean;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface IGoodsCommentListView {
    void goodsCommentList(GoodsCommentListBean goodsCommentListBean);
    void goodsCommentListOnError(String e);
}
