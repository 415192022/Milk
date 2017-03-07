package com.yundong.milk.user.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yundong.milk.R;
import com.yundong.milk.adapter.comment.GoodsCommentListAdapter;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.model.GoodsCommentListBean;
import com.yundong.milk.model.GoodsDetailsBean;
import com.yundong.milk.present.ActivityCommentListPresenter;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.util.rxbus.RxBus;
import com.yundong.milk.util.rxbus.Subscribe;
import com.yundong.milk.util.rxbus.ThreadMode;
import com.yundong.milk.view.IGoodsCommentListView;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lx on 2017/2/16.
 */

public class ActivityCommentList extends BaseActivity implements IGoodsCommentListView {
    private RecyclerView rv_comment_list;
    private GoodsCommentListAdapter goodsCommentListAdapter;
    private ActivityCommentListPresenter activityCommentListPresenter;

    public ActivityCommentList() {
    }

    @Override
    protected void onStart() {
        super.onStart();
        RxBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getDefault().unRegister(this);
    }

    private GoodsDetailsBean goodsDetailsBean;

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveGoodsInfo(GoodsDetailsBean goodsDetailsBean) {
        this.goodsDetailsBean = goodsDetailsBean;
        ToastUtil.showShortToast("===="+goodsDetailsBean.getData().getGoods_id());
        activityCommentListPresenter.goodsCommentList(goodsDetailsBean.getData().getGoods_id(),"1");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);

        rv_comment_list = (RecyclerView) findViewById(R.id.rv_comment_list);
        rv_comment_list.setHasFixedSize(true);
        rv_comment_list.setLayoutManager(new LinearLayoutManager(this));
        goodsCommentListAdapter = new GoodsCommentListAdapter(this);
        rv_comment_list.setAdapter(goodsCommentListAdapter);
        initTitle(R.string.commentList, true);
        activityCommentListPresenter = ActivityCommentListPresenter.getInstance().with(this);

    }

    @Override
    public void goodsCommentList(GoodsCommentListBean goodsCommentListBean) {
        Observable.from(goodsCommentListBean.getData().getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GoodsCommentListBean.GoodsCommentListData.GoodsCommentListDataArray>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GoodsCommentListBean.GoodsCommentListData.GoodsCommentListDataArray goodsCommentListDataArray) {
                        goodsCommentListAdapter.getGoodsCommentListDataArrays().add(goodsCommentListDataArray);
                        goodsCommentListAdapter.notifyDataSetChanged();
                    }
                });

    }

    @Override
    public void goodsCommentListOnError(String e) {
        ToastUtil.showShortToast(e);
    }
}
