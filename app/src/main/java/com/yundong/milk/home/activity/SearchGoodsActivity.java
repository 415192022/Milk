package com.yundong.milk.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.yundong.milk.R;
import com.yundong.milk.adapter.home.SearchResultAdapter;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.home.adapter.HotSearchAdapter;
import com.yundong.milk.model.HotSearchBean;
import com.yundong.milk.model.SearchResultBean;
import com.yundong.milk.present.SearchGoodsActivityPresenter;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.view.IHotSearchView;
import com.yundong.milk.view.ISearchResultView;
import com.yundong.milk.widget.swiprefreshlayout.SwipyRefreshLayout;
import com.yundong.milk.widget.swiprefreshlayout.SwipyRefreshLayoutDirection;

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
        IHotSearchView
        , ISearchResultView
        , SwipyRefreshLayout.OnRefreshListener {

    private GridView mGridView;
    private HotSearchAdapter mAdapter;

    private SearchGoodsActivityPresenter searchGoodsActivityPresenter;
    private RecyclerView rv_search_result;
    private SwipyRefreshLayout srl_search_result;
    private SearchResultAdapter searchResultAdapter;

    private EditText et_search_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_goods);
        findViewById(R.id.imgBack).setOnClickListener(this);
        findViewById(R.id.txtSearch).setOnClickListener(this);
        et_search_content = (EditText) findViewById(R.id.et_search_content);

        srl_search_result = (SwipyRefreshLayout) findViewById(R.id.srl_search_result);
        srl_search_result.setRefreshing(true);
        srl_search_result.setOnRefreshListener(this);
        srl_search_result.setDirection(SwipyRefreshLayoutDirection.BOTH);

        searchResultAdapter = new SearchResultAdapter(this);
        rv_search_result = (RecyclerView) findViewById(R.id.rv_search_result);
        rv_search_result.setHasFixedSize(true);
        rv_search_result.setLayoutManager(new GridLayoutManager(this, 2));
        rv_search_result.setAdapter(searchResultAdapter);

        mGridView = (GridView) findViewById(R.id.gridView);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                rv_search_result.setVisibility(View.VISIBLE);
                srl_search_result.setVisibility(View.VISIBLE);
                searchGoodsActivityPresenter.searchResult(mAdapter.getHotSearchBeens().get(i).getName(), "1");
            }
        });
        searchGoodsActivityPresenter = SearchGoodsActivityPresenter.getInstance().with(this, this);
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
                if ("".equals(et_search_content.getText().toString().trim())) {
                    ToastUtil.showShortToast("搜索商品名称不能为空！");
                    return;
                }
                rv_search_result.setVisibility(View.VISIBLE);
                srl_search_result.setVisibility(View.VISIBLE);
                searchGoodsActivityPresenter.searchResult(et_search_content.getText().toString().trim(), "1");
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
                        mAdapter = new HotSearchAdapter(SearchGoodsActivity.this, hotSearchBeens);
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

    private SearchResultBean searchResultBean;

    @Override
    public void searchResult(SearchResultBean searchResultBean) {
        this.searchResultBean = searchResultBean;
        Observable.from(searchResultBean.getData().getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchResultBean.SearchResultData.SearchResultArray>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SearchResultBean.SearchResultData.SearchResultArray searchResultArray) {
                        searchResultAdapter.getSearchResultArrays().add(searchResultArray);
                        searchResultAdapter.notifyDataSetChanged();
                    }
                });
        srl_search_result.setRefreshing(false);
    }

    @Override
    public void searchResultOnError(String e) {

    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        if (direction == SwipyRefreshLayoutDirection.TOP) {
            searchResultAdapter.getSearchResultArrays().clear();
            searchGoodsActivityPresenter.searchResult(et_search_content.getText().toString().trim(), "1");
        } else if (direction == SwipyRefreshLayoutDirection.BOTTOM) {
            if (null != searchResultBean) {
                if (searchResultBean.getData().getCurrent_page().equals(searchResultBean.getData().getTotal_page())) {
                    ToastUtil.showShortToast("没有更多数据");
                } else {
                    searchGoodsActivityPresenter
                            .searchResult(et_search_content
                                            .getText()
                                            .toString()
                                            .trim()
                                    , String.valueOf(Integer.parseInt(searchResultBean.getData().getCurrent_page()) + 1));
                }
            }
        }
        srl_search_result.setRefreshing(false);
    }
}
