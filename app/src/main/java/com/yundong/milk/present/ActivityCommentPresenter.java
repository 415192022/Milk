package com.yundong.milk.present;

import com.yundong.milk.interaptor.impl.OrderCommentImpl;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.view.IOrderCommentView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/3/7.
 */

public class ActivityCommentPresenter {
    private static  ActivityCommentPresenter activityCommentPresenter;

    //评论
    private OrderCommentImpl orderComment;
    private IOrderCommentView iOrderCommentView;

    private ActivityCommentPresenter(){
        orderComment=new OrderCommentImpl();
    }

    public static ActivityCommentPresenter getInstance(){
        activityCommentPresenter=new ActivityCommentPresenter();
        return activityCommentPresenter;
    }

    public ActivityCommentPresenter with(IOrderCommentView iOrderCommentView){
        this.iOrderCommentView=iOrderCommentView;
        return activityCommentPresenter;
    }

    public void orderComment(String user_id, String order_id, String comment_content, String comment_image, String fraction){
        orderComment.orderComment(user_id,order_id,comment_content,comment_image,fraction)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseReceiveBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iOrderCommentView.orderCommentOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseReceiveBean baseReceiveBean) {
                        iOrderCommentView.orderComment(baseReceiveBean);
                    }
                });
    }
}
