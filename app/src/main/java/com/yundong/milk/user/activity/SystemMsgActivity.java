package com.yundong.milk.user.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.user.adapter.SystemMsgAdapter;
import com.yundong.milk.widget.recyclerview.XRecyclerView;
import com.yundong.milk.widget.swiprefreshlayout.SwipyRefreshLayout;
import com.yundong.milk.widget.swiprefreshlayout.SwipyRefreshLayoutDirection;

/**
 * Created by lj on 2017/1/3.
 * 系统消息
 */
public class SystemMsgActivity extends BaseActivity implements XRecyclerView.LoadingListener{

    private RecyclerView rv_mycollection;
    private SwipyRefreshLayout srl_message_center;
    private SystemMsgAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_list);

        srl_message_center= (SwipyRefreshLayout) findViewById(R.id.srl_message_center);
        srl_message_center.setDirection(SwipyRefreshLayoutDirection.BOTH);
        srl_message_center.setRefreshing(true);

        initTitle(R.string.systemMsg, true);
        rv_mycollection = (RecyclerView) findViewById(R.id.rv_mycollection);
        rv_mycollection.setHasFixedSize(true);
        rv_mycollection.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SystemMsgAdapter(this);
        rv_mycollection.setAdapter(mAdapter);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
