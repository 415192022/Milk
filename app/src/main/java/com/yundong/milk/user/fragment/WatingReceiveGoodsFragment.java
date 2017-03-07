package com.yundong.milk.user.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yundong.milk.R;
import com.yundong.milk.widget.SwipeRefreshLoadMore;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public class WatingReceiveGoodsFragment extends Fragment implements SwipeRefreshLoadMore.OnRefreshListener,SwipeRefreshLoadMore.OnLoadListener{
    private RecyclerView rv_waiting_receive_goods;
    private SwipeRefreshLoadMore srl_waiting_receive_goods;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.layout_wating_receive_goods, null);
        srl_waiting_receive_goods= (SwipeRefreshLoadMore) view.findViewById(R.id.srl_waiting_receive_goods);
        srl_waiting_receive_goods.setOnRefreshListener(this);
        srl_waiting_receive_goods.setOnLoadListener(this);
        srl_waiting_receive_goods.setRefreshing(true);

        rv_waiting_receive_goods= (RecyclerView) view.findViewById(R.id.rv_waiting_receive_goods);
        rv_waiting_receive_goods.setHasFixedSize(true);
        rv_waiting_receive_goods.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoad() {

    }
}
