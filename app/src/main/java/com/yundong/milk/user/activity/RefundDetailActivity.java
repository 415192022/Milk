package com.yundong.milk.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;

/**
 * Created by lj on 2017/1/4.
 * 退款详情
 */
public class RefundDetailActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_detail);
        initTitle(R.string.refundDetail, true);
        findViewById(R.id.txtSeeMoneyTo).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.txtSeeMoneyTo:
                startActivity(new Intent(this, MoneyToWhereActivity.class));
                break;
        }
    }
}
