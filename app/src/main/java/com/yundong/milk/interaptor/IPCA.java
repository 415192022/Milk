package com.yundong.milk.interaptor;

import com.yundong.milk.model.PCABean;
import com.yundong.milk.model.RecommentTypeBean;

import rx.Observable;


/**
 * Created by MingweiLi on 2017/2/27.
 */

public interface IPCA {
    Observable<PCABean> getPCA(String area_parent_id);
}
