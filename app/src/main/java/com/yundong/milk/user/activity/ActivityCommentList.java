package com.yundong.milk.user.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by lx on 2017/2/16.
 * 评论列表
 */

public class ActivityCommentList extends BaseActivity implements IGoodsCommentListView, RadioGroup.OnCheckedChangeListener {
    private RecyclerView rv_comment_list;
    private GoodsCommentListAdapter goodsCommentListAdapter;
    private ActivityCommentListPresenter activityCommentListPresenter;
    private RadioGroup rg_comment_type;
    private TextView tv_comment_count;


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
        activityCommentListPresenter.goodsCommentList(goodsDetailsBean.getData().getGoods_id(), "1", "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);



        rb_all = (RadioButton) findViewById(R.id.rb_all);
        rb_good = (RadioButton) findViewById(R.id.rb_good);
        rb_middle = (RadioButton) findViewById(R.id.rb_middle);
        rb_pool = (RadioButton) findViewById(R.id.rb_pool);

        tv_comment_count = (TextView) findViewById(R.id.tv_comment_count);
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
    public void goodsCommentList(String gclb) {
        goodsCommentListAdapter.getGoodsCommentListDataArrays().clear();
        try {
            JSONObject jsonObject = new JSONObject(gclb);
            JSONObject data = jsonObject.getJSONObject("data");
            JSONObject _$0 = data.getJSONObject("0");
            JSONObject comment = data.getJSONObject("comment_sum");
            JSONArray dataArray = _$0.getJSONArray("data");
            if (isAll) {
                tv_comment_count.setText("评论(" + _$0.get("total") + ")");
                rb_all.setText("全部(" + _$0.get("total") + ")");
            }
            //好评统计
            rb_good.setText("好评(" + comment.get("Praise") + ")");
            //中评统计
            rb_middle.setText("中评(" + comment.get("Middle") + ")");
            //差评统计
            rb_pool.setText("差评(" + comment.get("difference") + ")");
            for (int i = 0; i < dataArray.length(); i++) {
                GoodsCommentListBean.DataBeanX._$0Bean.DataBean dataBean = JSON.parseObject(dataArray.get(i).toString(), GoodsCommentListBean.DataBeanX._$0Bean.DataBean.class);
                goodsCommentListAdapter.getGoodsCommentListDataArrays().add(dataBean);
                goodsCommentListAdapter.notifyDataSetChanged();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void goodsCommentListOnError(String e) {
        Log.i("LMW", e);
        ToastUtil.showShortToast(e);
    }

    private RadioButton rb_all;
    private RadioButton rb_good;
    private RadioButton rb_middle;
    private RadioButton rb_pool;
    private boolean isAll = true;

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        goodsCommentListAdapter.notifyDataSetChanged();
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.rb_all:
                //全部
                activityCommentListPresenter.goodsCommentList(goodsDetailsBean.getData().getGoods_id(), "1", "");
                break;
            case R.id.rb_good:
                //好评
                isAll = false;
                activityCommentListPresenter.goodsCommentList(goodsDetailsBean.getData().getGoods_id(), "1", "1");
                break;
            case R.id.rb_middle:
                isAll = false;
                //中评
                activityCommentListPresenter.goodsCommentList(goodsDetailsBean.getData().getGoods_id(), "1", "2");
                break;
            case R.id.rb_pool:
                //差评
                isAll = false;
                activityCommentListPresenter.goodsCommentList(goodsDetailsBean.getData().getGoods_id(), "1", "3");
                break;
        }
    }
}
