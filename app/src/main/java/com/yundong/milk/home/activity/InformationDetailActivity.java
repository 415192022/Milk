package com.yundong.milk.home.activity;

import android.os.Bundle;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;

/**
 * Created by lj on 2017/1/5.
 * 资讯详情
 */
public class InformationDetailActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_detail);
        initTitle(R.string.milkInformation, true);
    }
}
