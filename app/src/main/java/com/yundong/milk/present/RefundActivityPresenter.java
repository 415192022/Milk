package com.yundong.milk.present;

import com.yundong.milk.interaptor.impl.RefundImpl;
import com.yundong.milk.model.RefundBean;
import com.yundong.milk.view.IRefundView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public class RefundActivityPresenter {
    private static RefundActivityPresenter refundActivityPresenter;
    //退货列表
    private RefundImpl refund;
    private IRefundView iRefundView;

    private RefundActivityPresenter() {
        refund = new RefundImpl();
    }

    public static RefundActivityPresenter getInstance() {
        refundActivityPresenter = new RefundActivityPresenter();
        return refundActivityPresenter;
    }

    public RefundActivityPresenter with(IRefundView iRefundView) {
        this.iRefundView = iRefundView;
        return refundActivityPresenter;
    }

    //退货列表
    public void refundList(String user_id, String page, String page_data) {
        refund.refundList(user_id, page, page_data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RefundBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iRefundView.refundListOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(RefundBean refundBean) {
                        iRefundView.refundList(refundBean);
                    }
                });
    }


}
