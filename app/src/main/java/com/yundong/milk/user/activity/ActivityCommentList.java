package com.yundong.milk.user.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.TextView;

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

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lx on 2017/2/16.
 */

public class ActivityCommentList extends BaseActivity implements IGoodsCommentListView, RadioGroup.OnCheckedChangeListener {
    private RecyclerView rv_comment_list;
    private GoodsCommentListAdapter goodsCommentListAdapter;
    private ActivityCommentListPresenter activityCommentListPresenter;
    private RadioGroup rg_comment_type;
    private TextView tv_comment_count;
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
        activityCommentListPresenter.goodsCommentList(goodsDetailsBean.getData().getGoods_id(), "1","");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);

        tv_comment_count= (TextView) findViewById(R.id.tv_comment_count);
        rg_comment_type = (RadioGroup) findViewById(R.id.rg_comment_type);
        rg_comment_type.setOnCheckedChangeListener(this);
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
        tv_comment_count.setText("评论("+goodsCommentListBean.getData().getTotal()+")");
        Observable.from(goodsCommentListBean.getData().getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GoodsCommentListBean.GoodsCommentListData.GoodsCommentListArray>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        goodsCommentListAdapter.getGoodsCommentListDataArrays().clear();
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GoodsCommentListBean.GoodsCommentListData.GoodsCommentListArray goodsCommentListDataArray) {
                        goodsCommentListAdapter.getGoodsCommentListDataArrays().add(goodsCommentListDataArray);
                        goodsCommentListAdapter.notifyDataSetChanged();
                    }
                });

    }

    @Override
    public void goodsCommentListOnError(String e) {
        Log.i("LMW", e);
        ToastUtil.showShortToast(e);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        goodsCommentListAdapter.notifyDataSetChanged();
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.rb_all:
                //全部
                activityCommentListPresenter.goodsCommentList(goodsDetailsBean.getData().getGoods_id(), "1","");
                break;
            case R.id.rb_good:
                //好评
                activityCommentListPresenter.goodsCommentList(goodsDetailsBean.getData().getGoods_id(), "1","1");
                break;
            case R.id.rb_middle:
                //中评
                activityCommentListPresenter.goodsCommentList(goodsDetailsBean.getData().getGoods_id(), "1","2");
                break;
            case R.id.rb_pool:
                //差评
                activityCommentListPresenter.goodsCommentList(goodsDetailsBean.getData().getGoods_id(), "1","3");
                break;
        }
    }
}
