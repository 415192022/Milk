package com.yundong.milk.present;

import com.yundong.milk.interaptor.impl.BuyNowImpl;
import com.yundong.milk.model.BuyNowBean;
import com.yundong.milk.view.IBuyNowView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public class PaymentActivityPresenter {
    private static PaymentActivityPresenter paymentActivityPresenter;

    //立即支付
    private BuyNowImpl buyNow;
    private IBuyNowView iBuyNowView;

    private PaymentActivityPresenter() {
        buyNow = new BuyNowImpl();
    }

    public static PaymentActivityPresenter getInstance() {
        paymentActivityPresenter = new PaymentActivityPresenter();
        return paymentActivityPresenter;
    }

    public PaymentActivityPresenter with(IBuyNowView iBuyNowView) {
        this.iBuyNowView = iBuyNowView;
        return paymentActivityPresenter;
    }

    public void buyNow(String user_id, String goods_id, String number, String message) {
        buyNow.buyNow(user_id, goods_id, number, message)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BuyNowBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iBuyNowView.buyNowOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(BuyNowBean buyNowBean) {
                        iBuyNowView.buyNow(buyNowBean);
                    }
                });
    }
}
