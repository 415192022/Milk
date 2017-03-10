package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IMessageInfoService;
import com.yundong.milk.api.service.IOrderCommentService;
import com.yundong.milk.interaptor.IMessageInfo;
import com.yundong.milk.model.MessageInfoBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/9.
 */

public class MessageInfoImpl implements IMessageInfo {
    @Override
    public Observable<MessageInfoBean> messageInfo(String message_id, String user_id) {
        return RetrofitUtils.getInstance()
                .retrofitCtreate(URLConst.URL_MILK_BASE, IMessageInfoService.class)
                .messageInfo(user_id, user_id);
    }
}
