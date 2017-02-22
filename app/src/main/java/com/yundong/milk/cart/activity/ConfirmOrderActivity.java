package com.yundong.milk.cart.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;

/**
 * Created by lj on 2017/1/5.
 * 确认订单
 */
public class ConfirmOrderActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        initTitle(R.string.confirmDetail, true);
        ((TextView) findViewById(R.id.txtMarketPrice)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        findViewById(R.id.txtBuyIm).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.txtBuyIm:
                startActivity(new Intent(this, PaymentActivity.class));
                break;
        }
    }
}
