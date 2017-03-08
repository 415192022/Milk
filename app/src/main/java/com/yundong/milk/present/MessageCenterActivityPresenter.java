package com.yundong.milk.present;

import com.yundong.milk.interaptor.impl.MessageListImpl;
import com.yundong.milk.model.MessageListBean;
import com.yundong.milk.view.IMessageListView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/3/8.
 */

public class MessageCenterActivityPresenter {
    private static MessageCenterActivityPresenter messageCenterActivityPresenter;

    private MessageListImpl messageList;
    private IMessageListView iMessageListView;

    private MessageCenterActivityPresenter() {
        messageList = new MessageListImpl();
    }

    public static MessageCenterActivityPresenter getInstance() {
        messageCenterActivityPresenter = new MessageCenterActivityPresenter();
        return messageCenterActivityPresenter;
    }

    public MessageCenterActivityPresenter with(IMessageListView iMessageListView) {
        this.iMessageListView = iMessageListView;
        return messageCenterActivityPresenter;
    }

    public void messageList(String user_id
            , String message_type
            , String page
            , String page_data) {
        messageList.applyFroModify(user_id, message_type, page, page_data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MessageListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iMessageListView.messageListOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(MessageListBean messageListBean) {
                        iMessageListView.messageList(messageListBean);
                    }
                });

    }
}
