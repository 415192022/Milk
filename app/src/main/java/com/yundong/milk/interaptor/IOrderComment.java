package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.BuyNowBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface IOrderComment {
    Observable<BaseReceiveBean> orderComment(String user_id, String order_id, String comment_content, String comment_image, String fraction);
}
