package com.yundong.milk.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.user.adapter.TradeSuccessAdapter;
import com.yundong.milk.view.recyclerview.XRecyclerView;

/**
 * Created by lj on 2017/1/4.
 * 交易成功
 */
public class TradeSuccessActivity extends BaseActivity{

    private XRecyclerView mRecyclerView;
    private TradeSuccessAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_list);
        initTitle(R.string.tradeSuccess, true);
        mRecyclerView = (XRecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.initParams();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView.setLoadingMoreEnabled(false);
        View headView = View.inflate(this, R.layout.activity_trade_success_head, null);
        headView.findViewById(R.id.txtCommentIm).setOnClickListener(this);
        headView.findViewById(R.id.txtOrderDetail).setOnClickListener(this);
        mRecyclerView.addHeaderView(headView);
        mAdapter = new TradeSuccessAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.txtCommentIm://立即购买
                startActivity(new Intent(this, ApplyForMoney.class));
                break;
            case R.id.txtOrderDetail://订单详情
                startActivity(new Intent(this, RefundAfterSaleActivity.class));
                break;
        }
    }
}
