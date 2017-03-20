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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        // TODO Auto-generated method stub
        if (!isVisibleToUser) {
            //不可见
        } else {
            //可见
            maineOrderActivityPresenter = MineOrderFragmentPresenter.getInstance().with(this);
            if (null == allListAdapter) {
                maineOrderActivityPresenter.orderList(YunDongApplication.getLoginBean().getData().getUserinfo().getId(), "", "", "1", "20");
            } else {
                refresh();
            }
        }
        super.setUserVisibleHint(isVisibleToUser);
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
        allListAdapter = new OrderAllListAdapter(getActivity(), this);
        mRecyclerView.setAdapter(allListAdapter);
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

    }



    private OrderListBean orderListBean;

    @Override
    public void orderList(OrderListBean orderListBean) {
        this.orderListBean = orderListBean;
        Observable.from(orderListBean.getData().getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderListBean.OrderListData.OrderListDataArray>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        allListAdapter.getmList().clear();
                    }

                    @Override
                    public void onCompleted() {
                        srl_order_list.setRefreshing(false);
                        allListAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(OrderListBean.OrderListData.OrderListDataArray orderListDataArray) {
                        allListAdapter.getmList().add(orderListDataArray);
                        allListAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void orderListOnError(String e) {

    }

    public void refresh() {
        allListAdapter.getmList().clear();
        maineOrderActivityPresenter.orderList(YunDongApplication.getLoginBean().getData().getUserinfo().getId(), "", "", "1", "20");
    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        if (direction == SwipyRefreshLayoutDirection.TOP) {
            refresh();
        } else if (direction == SwipyRefreshLayoutDirection.BOTTOM) {
            if (null != orderListBean) {
                if (!orderListBean.getData().getLast_page().equals(orderListBean.getData().getCurrent_page())) {
                    maineOrderActivityPresenter.orderList(YunDongApplication.getLoginBean().getData().getUserinfo().getId(), "", "", String.valueOf(Integer.parseInt(orderListBean.getData().getCurrent_page()) + 1), "20");
                } else {
                    ToastUtil.showLongToast("没有更多订单信息。");
                }
            }

        }
    }
}
