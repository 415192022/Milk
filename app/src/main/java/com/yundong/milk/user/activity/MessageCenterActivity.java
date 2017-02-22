package com.yundong.milk.user.activity;

import android.os.Bundle;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.user.adapter.MessageCenterAdapter;
import com.yundong.milk.view.recyclerview.XRecyclerView;

/**
 * Created by lj on 2017/1/3.
 * 消息中心
 */
public class MessageCenterActivity extends BaseActivity{

    private XRecyclerView mRecyclerView;
    private MessageCenterAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_list);
        initTitle(R.string.messageCenter, true);
        mRecyclerView = (XRecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.initParams();
        mRecyclerView.setPullRefreshEnabled(false);
        mAdapter = new MessageCenterAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }
}
