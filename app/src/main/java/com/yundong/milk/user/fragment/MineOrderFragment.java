package com.yundong.milk.user.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yundong.milk.R;
import com.yundong.milk.manager.YunDongApplication;
import com.yundong.milk.model.OrderListBean;
import com.yundong.milk.present.MineOrderFragmentPresenter;
import com.yundong.milk.user.adapter.OrderListAdapter;
import com.yundong.milk.view.IOrderListView;
import com.yundong.milk.widget.recyclerview.XRecyclerView;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lj on 2016/11/14.
 * 我的订单
 */
public class MineOrderFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,IOrderListView {

    private XRecyclerView mRecyclerView;
    private OrderListAdapter mAdapter;
    private SwipeRefreshLayout srl_order_list;

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
        mRecyclerView = (XRecyclerView) view.findViewById(R.id.recyclerView);
        srl_order_list= (SwipeRefreshLayout) view.findViewById(R.id.srl_order_list);
        srl_order_list.setOnRefreshListener(this);
        srl_order_list.setRefreshing(true);
        mRecyclerView.initParams();
        mRecyclerView.setLoadingMoreEnabled(false);
        mRecyclerView.setPullRefreshEnabled(false);


        maineOrderActivityPresenter= MineOrderFragmentPresenter.getInstance().with(this);
        maineOrderActivityPresenter.orderList(YunDongApplication.getLoginBean().getData().getUserinfo().getId(),"4","评论","1","20");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        switch (getArguments().getInt("page")){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
        mAdapter = new OrderListAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onRefresh() {
        mAdapter.getmList().clear();
        orderListDataArrays.clear();
        maineOrderActivityPresenter.orderList("8","4","评论","1","20");

    }

    ArrayList<OrderListBean.OrderListData.OrderListDataArray> orderListDataArrays=new ArrayList<>();
    @Override
    public void orderList(OrderListBean orderListBean) {
        Observable.from(orderListBean.getData().getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderListBean.OrderListData.OrderListDataArray>() {
                    @Override
                    public void onCompleted() {
                        mAdapter.addData(orderListDataArrays);
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
}
