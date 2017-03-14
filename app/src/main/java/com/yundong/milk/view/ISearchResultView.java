package com.yundong.milk.view;

import com.yundong.milk.model.SearchResultBean;

/**
 * Created by MingweiLi on 2017/3/14.
 */

public interface ISearchResultView {
    void searchResult(SearchResultBean searchResultBean);

    void searchResultOnError(String e);
}
