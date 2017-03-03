package com.yundong.milk.view;

import com.yundong.milk.model.HotSearchBean;
import com.yundong.milk.model.RecommentTypeBean;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public interface IHotSearchView {
    void getHotSearch(HotSearchBean hotSearchBean);
    void getHotSearchOnError(String e);
}
