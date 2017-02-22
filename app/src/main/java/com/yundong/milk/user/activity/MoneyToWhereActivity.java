package com.yundong.milk.user.activity;

import android.os.Bundle;
import android.view.View;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;

/**
 * Created by lj on 2017/1/3.
 * 钱款去向
 */
public class MoneyToWhereActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_to_where);
        findViewById(R.id.imgBack).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.imgBack:
                finish();
                break;
        }
    }
}
