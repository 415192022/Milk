package com.yundong.milk.user.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.manager.YunDongApplication;
import com.yundong.milk.model.MessageListBean;
import com.yundong.milk.present.MessageCenterActivityPresenter;
import com.yundong.milk.user.adapter.MessageCenterAdapter;
import com.yundong.milk.view.IMessageListView;
import com.yundong.milk.widget.recyclerview.XRecyclerView;
import com.yundong.milk.widget.swiprefreshlayout.SwipyRefreshLayout;
import com.yundong.milk.widget.swiprefreshlayout.SwipyRefreshLayoutDirection;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lj on 2017/1/3.
 * 消息中心
 */
public class MessageCenterActivity extends BaseActivity implements IMessageListView,SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView rv_mycollection;
    private MessageCenterAdapter mAdapter;
    private SwipeRefreshLayout srl_message_center;

    private MessageCenterActivityPresenter messageCenterActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_message_center);
        initTitle(R.string.messageCenter, true);

        srl_message_center = (SwipeRefreshLayout) findViewById(R.id.srl_message_center);
        srl_message_center.setRefreshing(true);
        srl_message_center.setOnRefreshListener(this);

        rv_mycollection = (RecyclerView) findViewById(R.id.rv_mycollection);
        rv_mycollection.setHasFixedSize(true);
        rv_mycollection.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MessageCenterAdapter(this);
        rv_mycollection.setAdapter(mAdapter);

        messageCenterActivityPresenter = MessageCenterActivityPresenter.getInstance().with(this);
        messageCenterActivityPresenter.messageList(
                YunDongApplication.getLoginBean().getData().getUserinfo().getId()
                , "1", "1", "20"
        );
    }

    @Override
    public void messageList(MessageListBean messageListBean) {
        Observable.from(messageListBean.getData().getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MessageListBean.MessageListData.MessageListDataArray>() {
                    @Override
                    public void onStart() {
                        mAdapter.getMessageListDataArrays().clear();
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCompleted() {
                        srl_message_center.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MessageListBean.MessageListData.MessageListDataArray messageListDataArray) {
                        //得到每条消息
                        mAdapter.getMessageListDataArrays().add(messageListDataArray);
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void messageListOnError(String e) {

    }

    @Override
    public void onRefresh() {
        messageCenterActivityPresenter.messageList(
                YunDongApplication.getLoginBean().getData().getUserinfo().getId()
                , "1", "1", "20"
        );
    }
}
