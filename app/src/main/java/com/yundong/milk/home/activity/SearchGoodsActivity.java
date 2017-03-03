package com.yundong.milk.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.home.adapter.HotSearchAdapter;
import com.yundong.milk.model.HotSearchBean;
import com.yundong.milk.present.SearchGoodsActivityPresenter;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.view.IHotSearchView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lj on 2017/1/5.
 * 搜索
 */
public class SearchGoodsActivity extends BaseActivity
        implements
        IHotSearchView {

    private GridView mGridView;
    private HotSearchAdapter mAdapter;

    private SearchGoodsActivityPresenter searchGoodsActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_goods);
        findViewById(R.id.imgBack).setOnClickListener(this);
        findViewById(R.id.txtSearch).setOnClickListener(this);
        mGridView = (GridView) findViewById(R.id.gridView);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(SearchGoodsActivity.this, GoodsListTwoSortActivity.class));
            }
        });
        searchGoodsActivityPresenter = SearchGoodsActivityPresenter.getInstance().with(this);
        searchGoodsActivityPresenter.getHotSearch();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.txtSearch://搜索
                startActivity(new Intent(this, GoodsListTwoSortActivity.class));
                break;
        }
    }

    private List<HotSearchBean> hotSearchBeens = new ArrayList<>();

    @Override
    public void getHotSearch(HotSearchBean hotSearchBean) {
        Observable.from(hotSearchBean.getDatas())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HotSearchBean>() {
                    @Override
                    public void onCompleted() {
                        mAdapter = new HotSearchAdapter(SearchGoodsActivity.this,hotSearchBeens);
                        mGridView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(HotSearchBean hotSearchBean) {
                        hotSearchBeens.add(hotSearchBean);
                    }
                });
    }

    @Override
    public void getHotSearchOnError(String e) {
        ToastUtil.showShortToast("加载热门搜索数据失败!");
    }
}
