package com.yundong.milk.user.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.manager.YunDongApplication;
import com.yundong.milk.model.MessageInfoBean;
import com.yundong.milk.model.MessageListBean;
import com.yundong.milk.present.SystemMsgActivityPresenter;
import com.yundong.milk.user.adapter.SystemMsgAdapter;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.util.rxbus.RxBus;
import com.yundong.milk.util.rxbus.Subscribe;
import com.yundong.milk.util.rxbus.ThreadMode;
import com.yundong.milk.view.IMessageInfoView;
import com.yundong.milk.widget.recyclerview.XRecyclerView;
import com.yundong.milk.widget.swiprefreshlayout.SwipyRefreshLayout;
import com.yundong.milk.widget.swiprefreshlayout.SwipyRefreshLayoutDirection;

import rx.Observable;

/**
 * Created by lj on 2017/1/3.
 * 系统消息
 */
public class SystemMsgActivity extends BaseActivity
        implements
        IMessageInfoView,
        SwipeRefreshLayout.OnRefreshListener
{

    private RecyclerView rv_mycollection;
    private SwipeRefreshLayout srl_message_center;
    private SystemMsgAdapter mAdapter;

    private SystemMsgActivityPresenter systemMsgActivityPresenter;

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


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveMessageInfo(MessageListBean.MessageListData.MessageListDataArray messageInfoBean) {
        this.messageListDataArray=messageInfoBean;
        systemMsgActivityPresenter.messageInfo(messageInfoBean.getMessage_id()
                , YunDongApplication.getLoginBean().getData().getUserinfo().getId()
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_message_center);

        srl_message_center = (SwipeRefreshLayout) findViewById(R.id.srl_message_center);
        srl_message_center.setOnRefreshListener(this);
        srl_message_center.setRefreshing(true);

        initTitle(R.string.systemMsg, true);
        rv_mycollection = (RecyclerView) findViewById(R.id.rv_mycollection);
        rv_mycollection.setHasFixedSize(true);
        rv_mycollection.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SystemMsgAdapter(this);
        rv_mycollection.setAdapter(mAdapter);

        systemMsgActivityPresenter = SystemMsgActivityPresenter.getInstance().with(this);
    }

    @Override
    public void messageInfo(MessageInfoBean messageInfoBean) {
//        mAdapter.getMessageInfoBeen().add(messageInfoBean.getData());
//        mAdapter.notifyDataSetChanged();
//        srl_message_center.setRefreshing(false);
    }

    @Override
    public void messageInfoOnError(String e) {
        ToastUtil.showLongToast("消息详情获取失败!");
        srl_message_center.setRefreshing(false);
    }

    private MessageListBean.MessageListData.MessageListDataArray messageListDataArray;
    @Override
    public void onRefresh() {
        systemMsgActivityPresenter.messageInfo(messageListDataArray.getMessage_id()
                , YunDongApplication.getLoginBean().getData().getUserinfo().getId()
        );
    }
}
