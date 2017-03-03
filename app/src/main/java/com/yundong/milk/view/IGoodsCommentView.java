package com.yundong.milk.view;

import com.yundong.milk.model.GoodsCommentBean;

/**
 * Created by MingweiLi on 2017/3/2.
 */

public interface IGoodsCommentView {
    void getGoodsComment(GoodsCommentBean goodsCommentBean);
    void getGoodsCommentOnError(String e);
}
