package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.BuyNowBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface IApplyForModify {
    Observable<BaseReceiveBean> applyFroModify(String address_id);
}
