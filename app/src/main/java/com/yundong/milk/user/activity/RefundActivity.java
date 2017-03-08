package com.yundong.milk.user.activity;

import android.os.Bundle;
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

    private XRecyclerView mRecyclerView;
    private RefundListAdapter mAdapter;
    private SwipyRefreshLayout srl_refund;

    private RefundActivityPresenter refundActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_list);
        initTitle(R.string.refund, true);
        mRecyclerView = (XRecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView.setLoadingMoreEnabled(false);

        srl_refund = (SwipyRefreshLayout) findViewById(R.id.srl_message_center);
        srl_refund.setOnRefreshListener(this);
        srl_refund.setRefreshing(true);
        mRecyclerView.initParams();
        mAdapter = new RefundListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

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
        }
    }
}
