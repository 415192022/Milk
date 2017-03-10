package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;

import rx.Observable;


/**
 * Created by MingweiLi on 2017/2/27.
 */

public interface IReturnGoods {
    Observable<BaseReceiveBean> returnGoods(
            String user_id
            , String order_id
            , String return_reason
            , String return_img
    );
}
