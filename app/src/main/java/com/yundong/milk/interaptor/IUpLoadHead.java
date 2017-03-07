package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.TypeGoodsBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface IUpLoadHead {
    Observable<BaseReceiveBean> upLoadHead(String img, String user_id);
}
