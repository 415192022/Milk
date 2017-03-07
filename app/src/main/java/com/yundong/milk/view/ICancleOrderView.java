package com.yundong.milk.view;

import com.yundong.milk.model.BaseReceiveBean;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface ICancleOrderView {
    void cancleOrder(BaseReceiveBean baseReceiveBean);
    void cancleOrderOnError(String e);
}
