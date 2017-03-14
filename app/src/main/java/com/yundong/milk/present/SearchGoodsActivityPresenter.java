package com.yundong.milk.present;

import com.yundong.milk.interaptor.impl.HotSearchImpl;
import com.yundong.milk.interaptor.impl.SearchResultImpl;
import com.yundong.milk.model.HotSearchBean;
import com.yundong.milk.model.SearchResultBean;
import com.yundong.milk.view.IHotSearchView;
import com.yundong.milk.view.ISearchResultView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public class SearchGoodsActivityPresenter {
    private static SearchGoodsActivityPresenter searchGoodsActivityPresenter;

    //获得热门搜索
    private IHotSearchView iHotSearchView;
    private HotSearchImpl hotSearch;

    //搜索商品
    private SearchResultImpl searchResult;
    private ISearchResultView iSearchResultView;

    private SearchGoodsActivityPresenter() {
        hotSearch = new HotSearchImpl();
        searchResult = new SearchResultImpl();
    }

    public SearchGoodsActivityPresenter with(IHotSearchView iHotSearchView, ISearchResultView iSearchResultView) {
        this.iHotSearchView = iHotSearchView;
        this.iSearchResultView = iSearchResultView;
        return searchGoodsActivityPresenter;
    }

    public static SearchGoodsActivityPresenter getInstance() {
        searchGoodsActivityPresenter = new SearchGoodsActivityPresenter();
        return searchGoodsActivityPresenter;
    }

    public void getHotSearch() {
        hotSearch.getHotSearch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HotSearchBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iHotSearchView.getHotSearchOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(HotSearchBean hotSearchBean) {
                        iHotSearchView.getHotSearch(hotSearchBean);
                    }
                });
    }

    public void searchResult(
            String goods_name,
            String page
    ) {
        searchResult.searchResul(goods_name,page)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<SearchResultBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                iSearchResultView.searchResultOnError(e.getMessage());
            }

            @Override
            public void onNext(SearchResultBean searchResultBean) {
                iSearchResultView.searchResult(searchResultBean);
            }
        })
        ;

    }
}
