package com.yundong.milk.present;

import com.yundong.milk.interaptor.IApplyForModify;
import com.yundong.milk.interaptor.impl.ApplyForModifyImpl;
import com.yundong.milk.interaptor.impl.BuyNowImpl;
import com.yundong.milk.interaptor.impl.ReceiveGoodsAddressImpl;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.BuyNowBean;
import com.yundong.milk.model.ReceiveGoodsAddressBean;
import com.yundong.milk.view.IApplyForModifyView;
import com.yundong.milk.view.IBuyNowView;
import com.yundong.milk.view.IReceiveGoodsAddressView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/3/7.
 */

public class ConfirmOrderActivityPresenter {
    private static ConfirmOrderActivityPresenter confirmOrderActivityPresenter;
    //获取收货地址
    private ReceiveGoodsAddressImpl receiveGoodsAddress;
    private IReceiveGoodsAddressView iReceiveGoodsAddressView;

    //申请修改收货地址
    private ApplyForModifyImpl applyFroModify;
    private IApplyForModifyView iApplyForModifyView;

    //立即购买
    private BuyNowImpl buyNow;
    private IBuyNowView iBuyNowView;

    private ConfirmOrderActivityPresenter() {
        receiveGoodsAddress = new ReceiveGoodsAddressImpl();
        applyFroModify=new ApplyForModifyImpl();
        buyNow=new BuyNowImpl();
    }

    public ConfirmOrderActivityPresenter with(IReceiveGoodsAddressView iReceiveGoodsAddressView,IBuyNowView iBuyNowView) {
        this.iReceiveGoodsAddressView = iReceiveGoodsAddressView;
        this.iBuyNowView=iBuyNowView;
        return confirmOrderActivityPresenter;
    }
    public ConfirmOrderActivityPresenter with(IReceiveGoodsAddressView iReceiveGoodsAddressView,IApplyForModifyView iApplyForModifyView) {
        this.iReceiveGoodsAddressView = iReceiveGoodsAddressView;
        this.iApplyForModifyView=iApplyForModifyView;
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

    public void applyFroModify(String address_id){
        applyFroModify.applyFroModify(address_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseReceiveBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iApplyForModifyView.applyForModifyOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseReceiveBean baseReceiveBean) {
                        iApplyForModifyView.applyForModify(baseReceiveBean);
                    }
                });
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
