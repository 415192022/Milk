package com.yundong.milk.user.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yundong.milk.R;
import com.yundong.milk.adapter.RefundListAdapter;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.manager.YunDongApplication;
import com.yundong.milk.model.RefundBean;
import com.yundong.milk.present.RefundActivityPresenter;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.view.IRefundView;
import com.yundong.milk.widget.recyclerview.XRecyclerView;
import com.yundong.milk.widget.swiprefreshlayout.SwipyRefreshLayout;
import com.yundong.milk.widget.swiprefreshlayout.SwipyRefreshLayoutDirection;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lj on 2016/12/29.
 * 退款售后
 */
public class RefundActivity extends BaseActivity
        implements
        SwipyRefreshLayout.OnRefreshListener,
        IRefundView {

    private RecyclerView rv_refund;
    private RefundListAdapter mAdapter;
    private SwipyRefreshLayout srl_refund;

    private RefundActivityPresenter refundActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_refund_activity);
        initTitle(R.string.refund, true);
        rv_refund = (RecyclerView) findViewById(R.id.rv_refund);
        rv_refund.setHasFixedSize(true);
        rv_refund.setLayoutManager(new LinearLayoutManager(this));

        srl_refund = (SwipyRefreshLayout) findViewById(R.id.srl_refund);
        srl_refund.setDirection(SwipyRefreshLayoutDirection.BOTH);
        srl_refund.setOnRefreshListener(this);
        srl_refund.setRefreshing(true);
        mAdapter = new RefundListAdapter(this);
        rv_refund.setAdapter(mAdapter);

        refundActivityPresenter = RefundActivityPresenter.getInstance().with(this);
        refundActivityPresenter.refundList(YunDongApplication.getLoginBean().getData().getUserinfo().getId(), "1", "20");
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);

    }

    ArrayList<RefundBean.RefundData.RefundArray> refunLists = new ArrayList<>();

    @Override
    public void refundList(RefundBean refundBean) {
        Observable.from(refundBean.getData().getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RefundBean.RefundData.RefundArray>() {
                    @Override
                    public void onCompleted() {
                        mAdapter.addData(refunLists);
                        srl_refund.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RefundBean.RefundData.RefundArray refundArray) {
                        refunLists.add(refundArray);
                    }
                });
    }

    @Override
    public void refundListOnError(String e) {
        ToastUtil.showShortToast("加载退款数据失败");
    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        if (direction == SwipyRefreshLayoutDirection.TOP) {
            refunLists.clear();
            mAdapter.getmList().clear();
            refundActivityPresenter.refundList(YunDongApplication.getLoginBean().getData().getUserinfo().getId(), "1", "20");
        } else if (direction == SwipyRefreshLayoutDirection.BOTTOM) {
            ToastUtil.showShortToast("上拉加载");
            srl_refund.setRefreshing(false);
        }
    }

}
