package com.yundong.milk.cart.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.pingplusplus.android.Pingpp;
import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.manager.YunDongApplication;
import com.yundong.milk.model.CarListBean;
import com.yundong.milk.model.GoodsDetailsBean;
import com.yundong.milk.model.OrderListBean;
import com.yundong.milk.model.PingPayBean;
import com.yundong.milk.present.PaymentActivityPresenter;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.util.rxbus.RxBus;
import com.yundong.milk.util.rxbus.Subscribe;
import com.yundong.milk.util.rxbus.ThreadMode;
import com.yundong.milk.view.IPingPayView;

/**
 * Created by lj on 2017/1/5.
 * 支付
 */
public class PaymentActivity extends BaseActivity implements IPingPayView {

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
                    ((CheckBox) findViewById(R.id.cb_upacp)).setChecked(false);
                    ((CheckBox) findViewById(R.id.checkWeChat)).setChecked(false);
                }
            }
        });
        ((CheckBox) findViewById(R.id.checkWeChat)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
                if (isCheck) {
                    mPayStyle = 1;
                    ((CheckBox) findViewById(R.id.cb_upacp)).setChecked(false);
                    ((CheckBox) findViewById(R.id.checkAliPay)).setChecked(false);
                }
            }
        });
        ((CheckBox) findViewById(R.id.cb_upacp)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
                if (isCheck) {
                    mPayStyle = 2;
                    ((CheckBox) findViewById(R.id.checkAliPay)).setChecked(false);
                    ((CheckBox) findViewById(R.id.checkWeChat)).setChecked(false);
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
//                    Log.i("LMW", orderListDataArray.getOrder_sn());
                    paymentActivityPresenter.pingPay(
                            orderListDataArray.getOrder_sn()
                            , YunDongApplication.getLoginBean().getData().getUserinfo().getId()
                            , "alipay"
                    );


                } else if (mPayStyle == 1) {
                    //微信
                    paymentActivityPresenter.pingPay(
                            orderListDataArray.getOrder_sn()
                            , YunDongApplication.getLoginBean().getData().getUserinfo().getId()
                            , "wx"
                    );
                }
                else if (mPayStyle == 2) {
                    //银联
                    paymentActivityPresenter.pingPay(
                            orderListDataArray.getOrder_sn()
                            , YunDongApplication.getLoginBean().getData().getUserinfo().getId()
                            , "upacp"
                    );
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
    private String msg;
    private String totlePrice;
    private String goodsCount;
    private CarListBean.CarListDataA carListDataA;
    private OrderListBean.OrderListData.OrderListDataArray orderListDataArray;

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receive(OrderListBean.OrderListData.OrderListDataArray orderListDataArray) {
        this.orderListDataArray = orderListDataArray;
        ((TextView) findViewById(R.id.txtAllPrice)).setText(orderListDataArray.getOrder_amount());
    }


    @Override
    public void pingPay(PingPayBean pingPayBean) {
        Log.i("LMW", pingPayBean + "");
        String data = new Gson().toJson(pingPayBean);
        Pingpp.createPayment(PaymentActivity.this, data);
    }

    @Override
    public void pingPayOnError(String e) {

    }

    //支付结果的回调
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //支付页面返回处理
        if (requestCode == Pingpp.REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getExtras().getString("pay_result");
                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息

                // 处理返回值
                if (result.equals("success")) {
                    // "success" - 支付成功
                    finish();
                    ToastUtil.showLongToast("支付成功");
                } else if (result.equals("fail")) {
                    // "fail"    - 支付失败
                    finish();
                    ToastUtil.showLongToast("支付失败");
                } else if (result.equals("cancel")) {
                    // "cancel"  - 取消支付
                    ToastUtil.showLongToast("支付取消");
                } else if (result.equals("invalid")) {
                    // "invalid" - 支付插件未安装（一般是微信客户端未安装的情况）
                    ToastUtil.showLongToast("微信未安装");
                } else {
                    //错误信息
                    ToastUtil.showLongToast(errorMsg);
                }
            }
        }
    }
}
