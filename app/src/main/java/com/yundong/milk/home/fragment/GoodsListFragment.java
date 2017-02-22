package com.yundong.milk.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yundong.milk.R;
import com.yundong.milk.home.adapter.GoodsListAdapter;
import com.yundong.milk.view.recyclerview.XRecyclerView;

import java.util.ArrayList;


/**
 * Created by lj on 2016/11/7.
 * 首页
 */
public class GoodsListFragment extends Fragment implements XRecyclerView.LoadingListener, View.OnClickListener{

    private XRecyclerView mRecyclerView;
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private ArrayList<String> goodsSortList;
    private GoodsListAdapter mAdapter;

    public static GoodsListFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        GoodsListFragment pageFragment = new GoodsListFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.common_list_no_head, container, false);
        mRecyclerView = (XRecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.initParams();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setLoadingListener(this);
        mAdapter = new GoodsListAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onClick(View view) {

    }
}
