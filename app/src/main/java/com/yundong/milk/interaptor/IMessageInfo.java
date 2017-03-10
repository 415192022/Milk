package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.MessageInfoBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/8.
 */

public interface IMessageInfo {
    Observable<MessageInfoBean> messageInfo(
            String message_id
            , String user_id
    );
}
