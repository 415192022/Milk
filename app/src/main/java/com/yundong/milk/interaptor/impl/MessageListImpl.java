package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.ILoginService;
import com.yundong.milk.api.service.IMessageListService;
import com.yundong.milk.interaptor.IMessageList;
import com.yundong.milk.model.MessageListBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/8.
 */

public class MessageListImpl implements IMessageList {
    @Override
    public Observable<MessageListBean> applyFroModify(String user_id, String message_type, String page, String page_data) {
        return RetrofitUtils
                .getInstance()
                .retrofitCtreate(URLConst.URL_MILK_BASE, IMessageListService.class)
                .messageList(user_id, message_type, page, page_data);
    }
}
