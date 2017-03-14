package com.yundong.milk.user.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yundong.milk.R;
import com.yundong.milk.adapter.order.OrderAllListAdapter;
import com.yundong.milk.manager.YunDongApplication;
import com.yundong.milk.model.OrderListBean;
import com.yundong.milk.present.MineOrderFragmentPresenter;
import com.yundong.milk.user.adapter.OrderListAdapter;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.view.IOrderListView;
import com.yundong.milk.widget.recyclerview.XRecyclerView;
import com.yundong.milk.widget.swiprefreshlayout.SwipyRefreshLayout;
import com.yundong.milk.widget.swiprefreshlayout.SwipyRefreshLayoutDirection;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lj on 2016/11/14.
 * 我的订单
 */
public class MineOrderFragment extends Fragment implements SwipyRefreshLayout.OnRefreshListener, IOrderListView {

    private RecyclerView mRecyclerView;
    private OrderAllListAdapter allListAdapter;
    private SwipyRefreshLayout srl_order_list;

    public MineOrderFragmentPresenter maineOrderActivityPresenter;

    public static MineOrderFragment newInstance(int page) {
        MineOrderFragment fragment = new MineOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("page", page);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.common_list_no_head, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        srl_order_list = (SwipyRefreshLayout) view.findViewById(R.id.srl_order_list);
        srl_order_list.setDirection(SwipyRefreshLayoutDirection.BOTH);
        srl_order_list.setOnRefreshListener(this);
        srl_order_list.setRefreshing(true);


        maineOrderActivityPresenter = MineOrderFragmentPresenter.getInstance().with(this);
        maineOrderActivityPresenter.orderList(YunDongApplication.getLoginBean().getData().getUserinfo().getId(), "", "", "1", "20");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        switch (getArguments().getInt("page")) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
        allListAdapter = new OrderAllListAdapter(getActivity());
        mRecyclerView.setAdapter(allListAdapter);
    }


    ArrayList<OrderListBean.OrderListData.OrderListDataArray> orderListDataArrays = new ArrayList<>();

    @Override
    public void orderList(OrderListBean orderListBean) {
        Observable.from(orderListBean.getData().getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderListBean.OrderListData.OrderListDataArray>() {
                    @Override
                    public void onCompleted() {
                        allListAdapter.addData(orderListDataArrays);
                        srl_order_list.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(OrderListBean.OrderListData.OrderListDataArray orderListDataArray) {
                        orderListDataArrays.add(orderListDataArray);
                    }
                });
    }

    @Override
    public void orderListOnError(String e) {

    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        if (direction == SwipyRefreshLayoutDirection.TOP) {
            allListAdapter.getmList().clear();
            orderListDataArrays.clear();
            maineOrderActivityPresenter.orderList(YunDongApplication.getLoginBean().getData().getUserinfo().getId(), "", "", "1", "20");
        } else if (direction == SwipyRefreshLayoutDirection.BOTTOM) {
            srl_order_list.setRefreshing(false);
        }
    }
}
