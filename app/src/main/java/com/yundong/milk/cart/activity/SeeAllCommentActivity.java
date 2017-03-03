package com.yundong.milk.cart.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.yundong.milk.R;
import com.yundong.milk.api.MainApi;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.cart.adapter.GoodsCommentListAdapter;
import com.yundong.milk.cart.model.CommentListModel;
import com.yundong.milk.net.VolleyUtil;
import com.yundong.milk.util.JsonUtil;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.widget.recyclerview.XRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lj on 2016/11/28.
 * 查看所有评论
 */
public class SeeAllCommentActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, XRecyclerView.LoadingListener{

    private RadioGroup mRadCommentGroup;
    private XRecyclerView mRecyclerView;
    private GoodsCommentListAdapter mAdapter;
    private int mCurrentPage = 1;
    private int mType = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_comment);
        findViewById(R.id.imgBack).setOnClickListener(this);
        findViewById(R.id.imgMore).setOnClickListener(this);
        mRadCommentGroup = (RadioGroup) findViewById(R.id.radCommentGroup);
        mRadCommentGroup.setOnCheckedChangeListener(this);
        mRadCommentGroup.check(R.id.txtCommentAll);
        mRecyclerView = (XRecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.initParams();
        mRecyclerView.setLoadingListener(this);
        mAdapter = new GoodsCommentListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        getCommentList(mType);
        getCommentNum();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgBack:
                finish();
                break;
            case R.id.imgMore:
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
       switch (checkId){
           case R.id.txtCommentAll:
               mType = 3;
               getCommentList(mType);
               break;
           case R.id.txtCommentGood:
               mType = 2;
               getCommentList(mType);
               break;
           case R.id.txtCommentNeutral:
               mType = 1;
               getCommentList(mType);
               break;
           case R.id.txtCommentBad:
               mType = 0;
               getCommentList(mType);
               break;
       }
    }

    private void getCommentList(int type){
        HashMap<String, String> map = new HashMap<>();
        map.put("goods_id", getIntent().getStringExtra("goods_id"));
        map.put("type", String.valueOf(type));
        map.put("page", String.valueOf(mCurrentPage));
        VolleyUtil.SendVolleyPostBean(MainApi.GET_GOODS_COMMENT_LIST, map, new VolleyUtil.volleyInterface() {
            @Override
            public void ResponseResult(Object jsonObject) {
                JSONObject objectData = (JSONObject) jsonObject;
                try {
                    ArrayList<CommentListModel> list = JsonUtil.jsonToBeanList(objectData.getString("commentinfo"), CommentListModel.class);
                    if (list.size() < 10) {
                        mRecyclerView.noMoreLoading();
                    } else {
                        mRecyclerView.hasMoreLoading();
                    }
                    if (mCurrentPage == 1) {
                        mAdapter.removeData();
                    }
                    mAdapter.addData(list);
                    mRecyclerView.refreshComplete();
                    mRecyclerView.loadMoreComplete();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void ResponseError(Object volleyError) {
                ToastUtil.showShortToast(volleyError.toString());
            }
        });
    }

    private void getCommentNum(){
        HashMap<String, String> map = new HashMap<>();
        map.put("goods_id", getIntent().getStringExtra("goods_id"));
        VolleyUtil.SendVolleyPostBean(MainApi.GET_COMMENT_NUMBER, map, new VolleyUtil.volleyInterface() {
            @Override
            public void ResponseResult(Object jsonObject) {
                JSONObject objectData = (JSONObject) jsonObject;
                try {
                    ((TextView) findViewById(R.id.txtCommentAll)).setText("全部(" + objectData.getString("qb") + ")");
                    ((TextView) findViewById(R.id.txtCommentGood)).setText("好评(" + objectData.getString("hp") + ")");
                    ((TextView) findViewById(R.id.txtCommentNeutral)).setText("中评(" + objectData.getString("zp") + ")");
                    ((TextView) findViewById(R.id.txtCommentBad)).setText("差评(" + objectData.getString("cp") + ")");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void ResponseError(Object volleyError) {
                ToastUtil.showShortToast(volleyError.toString());
            }
        });
    }

    @Override
    public void onRefresh() {
        mCurrentPage = 1;
        getCommentList(mType);
    }

    @Override
    public void onLoadMore() {
        mCurrentPage ++;
        getCommentList(mType);
    }
}
