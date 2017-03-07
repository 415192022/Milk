package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/2.
 */

public interface IFeedBack {
    Observable<BaseReceiveBean> feedBack(String user_id, String comment, String phone);
}
