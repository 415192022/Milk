package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface IModifyNacikName {
    Observable<BaseReceiveBean> modifyNacikName(String name, String user_id);
}
