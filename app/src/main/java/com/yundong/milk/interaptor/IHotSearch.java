package com.yundong.milk.interaptor;

import com.yundong.milk.model.HotSearchBean;
import com.yundong.milk.model.RecommentTypeBean;

import rx.Observable;


/**
 * Created by MingweiLi on 2017/2/27.
 */

public interface IHotSearch {
    Observable<HotSearchBean> getHotSearch();
}
