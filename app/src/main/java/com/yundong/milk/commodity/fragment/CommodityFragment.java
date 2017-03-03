package com.yundong.milk.commodity.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseFragment;
import com.yundong.milk.commodity.adapter.CommodityListAdapter;
import com.yundong.milk.widget.recyclerview.XRecyclerView;

/**
 * Created by lj on 2016/12/12.
 * 商品
 */
public class CommodityFragment extends BaseFragment {

    private XRecyclerView mRecyclerView;
    private CommodityListAdapter mAdapter;

    @Override
    public int getRootView() {
        return R.layout.fragment_commodity;
    }

    @Override
    public void initView(View view) {
        mRecyclerView = (XRecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.initParams();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView.setLoadingMoreEnabled(false);
        mAdapter = new CommodityListAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }
}