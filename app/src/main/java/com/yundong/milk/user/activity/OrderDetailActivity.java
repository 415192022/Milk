package com.yundong.milk.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;

/**
 * Created by lj on 2017/1/3.
 * 订单详情
 */
public class OrderDetailActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        findViewById(R.id.imgBack).setOnClickListener(this);
        findViewById(R.id.txtBuyIm).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.imgBack:
                finish();
                break;
            case R.id.txtBuyIm:
                startActivity(new Intent(this, RefundDetailActivity.class));
                break;
        }
    }
}
