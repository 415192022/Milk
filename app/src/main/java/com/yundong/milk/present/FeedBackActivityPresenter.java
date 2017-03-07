package com.yundong.milk.present;

import com.yundong.milk.interaptor.impl.FeedBackImpl;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.view.IFeedBackView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/3/7.
 */

public class FeedBackActivityPresenter {
    private static FeedBackActivityPresenter feedBackActivityPresenter;

    //意见反馈
    private IFeedBackView iFeedBackView;
    private FeedBackImpl feedBack;

    private FeedBackActivityPresenter(){
        feedBack=new FeedBackImpl();
    }

    public static FeedBackActivityPresenter getInstance(){
        feedBackActivityPresenter=new FeedBackActivityPresenter();
        return feedBackActivityPresenter;
    }

    public FeedBackActivityPresenter with(IFeedBackView iFeedBackView){
        this.iFeedBackView=iFeedBackView;
        return feedBackActivityPresenter;
    }

    public void feedBack(String user_id, String comment, String phone){
        feedBack.feedBack(user_id,comment,phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseReceiveBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iFeedBackView.feedBackOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseReceiveBean baseReceiveBean) {
                        iFeedBackView.feedBack(baseReceiveBean);
                    }
                });
    }
}
