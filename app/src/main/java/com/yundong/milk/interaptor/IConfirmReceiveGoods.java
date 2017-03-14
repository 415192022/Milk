package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.RecommentTypeBean;

import rx.Observable;


/**
 * Created by MingweiLi on 2017/2/27.
 */

public interface IConfirmReceiveGoods {
    Observable<BaseReceiveBean> confirmReceiveGoods(String order_id);
}
