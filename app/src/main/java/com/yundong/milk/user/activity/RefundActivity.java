package com.yundong.milk.user.activity;

import android.os.Bundle;
import android.view.View;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.user.adapter.RefundListAdapter;
import com.yundong.milk.view.recyclerview.XRecyclerView;

/**
 * Created by lj on 2016/12/29.
 * 退款售后
 */
public class RefundActivity extends BaseActivity implements XRecyclerView.LoadingListener{

    private XRecyclerView mRecyclerView;
    private RefundListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_list);
        initTitle(R.string.refund, true);
        mRecyclerView = (XRecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.initParams();
        mRecyclerView.setLoadingListener(this);
        mAdapter = new RefundListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
