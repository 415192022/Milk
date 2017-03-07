package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IBuyNowService;
import com.yundong.milk.api.service.IOrderCommentService;
import com.yundong.milk.interaptor.IBuyNow;
import com.yundong.milk.interaptor.IOrderComment;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.BuyNowBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public class OrderCommentImpl implements IOrderComment {

    @Override
    public Observable<BaseReceiveBean> orderComment(String user_id, String order_id, String comment_content, String comment_image, String fraction) {
        return RetrofitUtils.getInstance()
                .retrofitCtreate(URLConst.URL_MILK_BASE, IOrderCommentService.class)
                .orderComment(user_id, order_id, comment_content, comment_image, fraction);
    }
}
