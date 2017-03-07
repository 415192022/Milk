package com.yundong.milk.present;

import com.yundong.milk.interaptor.impl.ReceiveGoodsAddressImpl;
import com.yundong.milk.model.ReceiveGoodsAddressBean;
import com.yundong.milk.view.IReceiveGoodsAddressView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/3/7.
 */

public class ConfirmOrderActivityPresenter {
    private static ConfirmOrderActivityPresenter confirmOrderActivityPresenter;

    private ReceiveGoodsAddressImpl receiveGoodsAddress;
    private IReceiveGoodsAddressView iReceiveGoodsAddressView;

    private ConfirmOrderActivityPresenter() {
        receiveGoodsAddress = new ReceiveGoodsAddressImpl();
    }

    public ConfirmOrderActivityPresenter with(IReceiveGoodsAddressView iReceiveGoodsAddressView) {
        this.iReceiveGoodsAddressView = iReceiveGoodsAddressView;
        return confirmOrderActivityPresenter;
    }

    public static ConfirmOrderActivityPresenter getInstance() {
        confirmOrderActivityPresenter = new ConfirmOrderActivityPresenter();
        return confirmOrderActivityPresenter;
    }

    public void receiveGoodsAddress(String user_id) {
        receiveGoodsAddress.receiveGoodsAddress(user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReceiveGoodsAddressBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iReceiveGoodsAddressView.receiveGoodsAddressOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(ReceiveGoodsAddressBean receiveGoodsAddressBean) {
                        iReceiveGoodsAddressView.receiveGoodsAddress(receiveGoodsAddressBean);
                    }
                });
    }
}
