package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.BaseReceivesBean;
import com.yundong.milk.model.TypeBrandBean;

import rx.Observable;


/**
 * Created by MingweiLi on 2017/2/27.
 */

public interface ITypeBrand {
    Observable<TypeBrandBean> getTypeBrandBean(String class_id);
}
