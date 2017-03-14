package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.SearchResultBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/14.
 */

public interface ISearchResul {
    Observable<SearchResultBean> searchResul(
            String goods_name
            , String page
    );
}
