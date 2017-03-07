package com.yundong.milk.view;

import com.yundong.milk.model.BaseReceiveBean;

/**
 * Created by MingweiLi on 2017/3/7.
 */

public interface IFeedBackView {
    void feedBack(BaseReceiveBean baseReceiveBean);
    void feedBackOnError(String e);
}
