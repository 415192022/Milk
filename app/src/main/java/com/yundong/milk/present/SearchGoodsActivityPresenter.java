package com.yundong.milk.present;

import com.yundong.milk.interaptor.impl.HotSearchImpl;
import com.yundong.milk.model.HotSearchBean;
import com.yundong.milk.view.IHotSearchView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public class SearchGoodsActivityPresenter {
    private static SearchGoodsActivityPresenter searchGoodsActivityPresenter;

    private IHotSearchView iHotSearchView;
    private HotSearchImpl hotSearch;

    private SearchGoodsActivityPresenter() {
        hotSearch = new HotSearchImpl();
    }

    public SearchGoodsActivityPresenter with(IHotSearchView iHotSearchView) {
        this.iHotSearchView = iHotSearchView;
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
}
