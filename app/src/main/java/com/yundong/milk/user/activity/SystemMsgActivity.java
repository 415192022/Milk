package com.yundong.milk.user.activity;

import android.os.Bundle;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.user.adapter.SystemMsgAdapter;
import com.yundong.milk.widget.recyclerview.XRecyclerView;

/**
 * Created by lj on 2017/1/3.
 * 系统消息
 */
public class SystemMsgActivity extends BaseActivity implements XRecyclerView.LoadingListener{

    private XRecyclerView mRecyclerView;
    private SystemMsgAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_list);
        initTitle(R.string.systemMsg, true);
        mRecyclerView = (XRecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.initParams();
        mRecyclerView.setLoadingListener(this);
        mAdapter = new SystemMsgAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
