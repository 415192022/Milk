package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.MessageListBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface IMessageList {
    Observable<MessageListBean> applyFroModify(
            String user_id
            ,String message_type
            ,String page
            ,String page_data
    );
}
