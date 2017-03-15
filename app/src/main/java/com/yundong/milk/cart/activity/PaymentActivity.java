package com.yundong.milk.cart.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.manager.YunDongApplication;
import com.yundong.milk.model.BuyNowBean;
import com.yundong.milk.model.CarListBean;
import com.yundong.milk.model.GoodsAndAddressBean;
import com.yundong.milk.model.GoodsDetailsBean;
import com.yundong.milk.model.OrderListBean;
import com.yundong.milk.model.ReceiveGoodsAddressBean;
import com.yundong.milk.present.PaymentActivityPresenter;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.util.rxbus.RxBus;
import com.yundong.milk.util.rxbus.Subscribe;
import com.yundong.milk.util.rxbus.ThreadMode;
import com.yundong.milk.view.IBuyNowView;

/**
 * Created by lj on 2017/1/5.
 * 支付
 */
public class PaymentActivity extends BaseActivity implements IBuyNowView {

    private int mPayStyle = 0;
    private PaymentActivityPresenter paymentActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initTitle(R.string.pay_method, true);
        findViewById(R.id.txtBuyIm).setOnClickListener(this);
        ((CheckBox) findViewById(R.id.checkAliPay)).setChecked(true);
        ((CheckBox) findViewById(R.id.checkAliPay)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
                if (isCheck) {
                    mPayStyle = 0;
                    ((CheckBox) findViewById(R.id.checkWeChat)).setChecked(false);
                }
            }
        });
        ((CheckBox) findViewById(R.id.checkWeChat)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
                if (isCheck) {
                    mPayStyle = 1;
                    ((CheckBox) findViewById(R.id.checkAliPay)).setChecked(false);
                }
            }
        });

        paymentActivityPresenter = PaymentActivityPresenter.getInstance().with(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.txtBuyIm:
                if (mPayStyle == 0) { //支付宝
                    if (null != goodsDetailsBean) {
                        paymentActivityPresenter.buyNow(YunDongApplication.getLoginBean().getData().getUserinfo()
                                        .getId()
                                , goodsDetailsBean.getData().getGoods_id(), goodsCount, msg);

                    }
                    if (null != carListDataA) {
                        paymentActivityPresenter.buyNow(YunDongApplication.getLoginBean().getData().getUserinfo()
                                        .getId()
                                , carListDataA.getGoods_id(), goodsCount, msg
                        );
                    }
                    if (null != orderListDataArray) {
                        paymentActivityPresenter.buyNow(YunDongApplication.getLoginBean().getData().getUserinfo()
                                        .getId()
                                , orderListDataArray.getGoods_id(), goodsCount, msg);
                    }

                } else if (mPayStyle == 1) {
                    //微信
                    if (null != goodsDetailsBean) {
                        paymentActivityPresenter.buyNow(YunDongApplication.getLoginBean().getData().getUserinfo()
                                        .getId()
                                , goodsDetailsBean.getData().getGoods_id(), goodsCount, msg);

                    }
                    if (null != carListDataA) {
                        paymentActivityPresenter.buyNow(YunDongApplication.getLoginBean().getData().getUserinfo()
                                        .getId()
                                , carListDataA.getGoods_id(), goodsCount, msg
                        );
                    }
                    if (null != orderListDataArray) {
                        paymentActivityPresenter.buyNow(YunDongApplication.getLoginBean().getData().getUserinfo()
                                        .getId()
                                , orderListDataArray.getGoods_id(), goodsCount, msg);
                    }
                }
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        RxBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getDefault().unRegister(this);
    }

    private GoodsDetailsBean goodsDetailsBean;
    private ReceiveGoodsAddressBean receiveGoodsAddressBean;
    private String msg;
    private String totlePrice;
    private String goodsCount;
    private CarListBean.CarListDataA carListDataA;
    private OrderListBean.OrderListData.OrderListDataArray orderListDataArray;

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receive(GoodsAndAddressBean goodsAndAddressBean) {
        receiveGoodsAddressBean = goodsAndAddressBean.getReceiveGoodsAddressBean();
        carListDataA = goodsAndAddressBean.getCarListDataA();
        orderListDataArray = goodsAndAddressBean.getOrderListDataArray();
        goodsDetailsBean = goodsAndAddressBean.getGoodsDetailsBean();
        msg = goodsAndAddressBean.getMsg();
        totlePrice = goodsAndAddressBean.getTotlePrice();
        goodsCount = goodsAndAddressBean.getCount();
        ((TextView)findViewById(R.id.txtAllPrice)).setText(goodsAndAddressBean.getTotlePrice());
    }


    @Override
    public void buyNow(BuyNowBean buyNowBean) {
        ToastUtil.showShortToast(buyNowBean.getMsg());
        finish();
    }

    @Override
    public void buyNowOnError(String e) {

    }
}
