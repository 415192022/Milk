package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IReturnGoodsService;
import com.yundong.milk.api.service.ISearchResultService;
import com.yundong.milk.interaptor.ISearchResul;
import com.yundong.milk.model.SearchResultBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/14.
 */

public class SearchResultImpl implements ISearchResul {
    @Override
    public Observable<SearchResultBean> searchResul(String goods_name, String page) {
        return RetrofitUtils.getInstance()
                .retrofitCtreate(URLConst.URL_MILK_BASE, ISearchResultService.class)
                .searchResult(goods_name, page);
    }
}
