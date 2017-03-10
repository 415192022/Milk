package com.yundong.milk.view;

import com.yundong.milk.model.MessageInfoBean;

/**
 * Created by MingweiLi on 2017/3/9.
 */

public interface IMessageInfoView {
    void messageInfo(MessageInfoBean messageInfoBean);
    void messageInfoOnError(String e);
}
