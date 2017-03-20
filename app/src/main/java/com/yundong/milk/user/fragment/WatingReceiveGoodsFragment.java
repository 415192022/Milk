package com.yundong.milk.user.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yundong.milk.R;
import com.yundong.milk.adapter.order.OrderWaitingReceiveListAdapter;
import com.yundong.milk.manager.YunDongApplication;
import com.yundong.milk.model.OrderListBean;
import com.yundong.milk.present.MineOrderFragmentPresenter;
import com.yundong.milk.user.adapter.OrderListAdapter;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.view.IOrderListView;
import com.yundong.milk.widget.swiprefreshlayout.SwipyRefreshLayout;
import com.yundong.milk.widget.swiprefreshlayout.SwipyRefreshLayoutDirection;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/3/6.
 * 待收货
 */

public class WatingReceiveGoodsFragment extends Fragment implements SwipyRefreshLayout.OnRefreshListener, IOrderListView {
    private RecyclerView mRecyclerView;
    private OrderWaitingReceiveListAdapter orderWaitingReceiveListAdapter;
    private SwipyRefreshLayout srl_order_list;

    public MineOrderFragmentPresenter maineOrderActivityPresenter;

    public static WatingReceiveGoodsFragment newInstance(int page) {
        WatingReceiveGoodsFragment fragment = new WatingReceiveGoodsFragment();
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
            maineOrderActivityPresenter.orderList(YunDongApplication.getLoginBean().getData().getUserinfo().getId(), "3", "", "1", "20");
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
        orderWaitingReceiveListAdapter = new OrderWaitingReceiveListAdapter(getActivity());
        mRecyclerView.setAdapter(orderWaitingReceiveListAdapter);
    }


    ArrayList<OrderListBean.OrderListData.OrderListDataArray> orderListDataArrays = new ArrayList<>();

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
                        orderWaitingReceiveListAdapter.getmList().clear();
                        orderWaitingReceiveListAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCompleted() {
                        srl_order_list.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(OrderListBean.OrderListData.OrderListDataArray orderListDataArray) {
                        orderWaitingReceiveListAdapter.getmList().add(orderListDataArray);
                        orderWaitingReceiveListAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void orderListOnError(String e) {

    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        if (direction == SwipyRefreshLayoutDirection.TOP) {
            orderWaitingReceiveListAdapter.getmList().clear();
            orderListDataArrays.clear();
            maineOrderActivityPresenter.orderList(YunDongApplication.getLoginBean().getData().getUserinfo().getId(), "3", "", "1", "20");
        } else if (direction == SwipyRefreshLayoutDirection.BOTTOM) {
            srl_order_list.setRefreshing(false);

            if (null != orderListBean) {
                if (!orderListBean.getData().getLast_page().equals(orderListBean.getData().getCurrent_page())) {
                    maineOrderActivityPresenter.orderList(YunDongApplication.getLoginBean().getData().getUserinfo().getId(), "3", "", String.valueOf(Integer.parseInt(orderListBean.getData().getCurrent_page()) + 1), "20");
                } else {
                    ToastUtil.showLongToast("没有更多订单信息。");
                }
            }
        }
    }
}
