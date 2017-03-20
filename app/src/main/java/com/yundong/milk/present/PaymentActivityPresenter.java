package com.yundong.milk.present;

import com.yundong.milk.interaptor.impl.BuyNowImpl;
import com.yundong.milk.interaptor.impl.PingPayImpl;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.BuyNowBean;
import com.yundong.milk.model.PingPayBean;
import com.yundong.milk.view.IBuyNowView;
import com.yundong.milk.view.IPingPayView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public class PaymentActivityPresenter {
    private static PaymentActivityPresenter paymentActivityPresenter;

    //请求支付
    private IPingPayView iPingPayView;
    private PingPayImpl pingPay;

    private PaymentActivityPresenter() {
        pingPay = new PingPayImpl();
    }

    public static PaymentActivityPresenter getInstance() {
        paymentActivityPresenter = new PaymentActivityPresenter();
        return paymentActivityPresenter;
    }

    public PaymentActivityPresenter with(IPingPayView iPingPayView) {
        this.iPingPayView = iPingPayView;
        return paymentActivityPresenter;
    }

    public void pingPay(String order_no
            , String user_id
            , String channel) {
        pingPay.pingPay(order_no, user_id, channel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PingPayBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iPingPayView.pingPayOnError(e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(PingPayBean pingPayBean) {
                        iPingPayView.pingPay(pingPayBean);
                    }
                });
    }

}
