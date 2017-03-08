package com.yundong.milk.view;

import com.yundong.milk.model.MessageListBean;

/**
 * Created by MingweiLi on 2017/3/8.
 */

public interface IMessageListView {
    void messageList(MessageListBean messageListBean);
    void messageListOnError(String e);
}
