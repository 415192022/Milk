package com.yundong.milk.user.activity;

import android.os.Bundle;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.view.recyclerview.XRecyclerView;

/**
 * Created by lj on 2017/1/3.
 * 活动消息
 */
public class CommentMsgActivity extends BaseActivity implements XRecyclerView.LoadingListener{

    private XRecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_list);
        initTitle(R.string.commentMsg, true);
        mRecyclerView = (XRecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.initParams();
        mRecyclerView.setLoadingListener(this);
    }

    @Override
    public void onRefresh() {
        
    }

    @Override
    public void onLoadMore() {

    }
}
