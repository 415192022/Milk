package com.yundong.milk.user.activity;

import android.os.Bundle;
import android.view.View;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.cache.CacheActivity;

/**
 * Created by lj on 2016/11/20.
 * 意见反馈成功
 */
public class FeedBackComActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_complete);
        findViewById(R.id.imageLeft).setOnClickListener(this);
        findViewById(R.id.btnSure).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageLeft:
                finish();
                CacheActivity.finishSingleActivityByClass(FeedBackActivity.class);
                break;
            case R.id.btnSure:
                finish();
                CacheActivity.finishSingleActivityByClass(FeedBackActivity.class);
                break;
        }
    }
}
