package com.yundong.milk.view;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.GoodsClassCommentBean;

/**
 * Created by MingweiLi on 2017/3/3.
 */

public interface IGoodsClassCommentView {
    void getGoodsClassComment(GoodsClassCommentBean goodsClassCommentBean);
    void getGoodsClassCommentOnError(String e);
}
