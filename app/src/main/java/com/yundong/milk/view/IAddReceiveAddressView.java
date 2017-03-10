package com.yundong.milk.view;

import com.yundong.milk.model.BaseReceiveBean;

/**
 * Created by MingweiLi on 2017/3/10.
 */

public interface IAddReceiveAddressView {
    void addReceiveAddress(BaseReceiveBean baseReceiveBean);
    void addReceiveAddressOnError(String e);
}
