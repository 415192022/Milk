package com.yundong.milk.view;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.BuyNowBean;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface IOrderCommentView {
    void orderComment(BaseReceiveBean baseReceiveBean);
    void orderCommentOnError(String e);
}
