package com.yundong.milk.present;

import com.yundong.milk.interaptor.impl.MessageInfoImpl;
import com.yundong.milk.interaptor.impl.MessageListImpl;
import com.yundong.milk.model.MessageInfoBean;
import com.yundong.milk.view.IMessageInfoView;
import com.yundong.milk.view.IMessageListView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/3/9.
 */

public class SystemMsgActivityPresenter {
    private static SystemMsgActivityPresenter systemMsgActivityPresenter;

    //获得消息详情
    private MessageInfoImpl messageInfo;
    private IMessageInfoView iMessageInfoView;

    public static SystemMsgActivityPresenter getInstance() {
        systemMsgActivityPresenter = new SystemMsgActivityPresenter();
        return systemMsgActivityPresenter;
    }

    private SystemMsgActivityPresenter() {
        messageInfo = new MessageInfoImpl();
    }

    public SystemMsgActivityPresenter with(IMessageInfoView iMessageInfoView) {
        this.iMessageInfoView = iMessageInfoView;
        return systemMsgActivityPresenter;
    }

    public void messageInfo(String message_id, String user_id) {
        messageInfo.messageInfo(message_id, user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MessageInfoBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iMessageInfoView.messageInfoOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(MessageInfoBean messageInfoBean) {
                        iMessageInfoView.messageInfo(messageInfoBean);
                    }
                });
    }
}
