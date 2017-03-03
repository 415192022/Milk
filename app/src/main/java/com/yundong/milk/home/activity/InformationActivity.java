package com.yundong.milk.home.activity;

import android.os.Bundle;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.home.adapter.InformationListAdapter;
import com.yundong.milk.widget.recyclerview.XRecyclerView;

/**
 * Created by lj on 2017/1/5.
 * 资讯
 */
public class InformationActivity extends BaseActivity implements XRecyclerView.LoadingListener{

    private XRecyclerView mRecyclerView;
    private InformationListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_list);
        initTitle(R.string.milkInformation, true);
        mRecyclerView = (XRecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.initParams();
        mRecyclerView.setLoadingListener(this);
        mAdapter = new InformationListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
