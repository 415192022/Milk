package com.yundong.milk.cart.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;

/**
 * Created by lj on 2017/1/5.
 * 支付
 */
public class PaymentActivity extends BaseActivity {

    private int mPayStyle = 0;

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
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.txtBuyIm:
                if (mPayStyle == 0) { //支付宝

                } else if (mPayStyle == 1) { //微信

                }
                break;
        }
    }
}
