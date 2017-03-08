package com.yundong.milk.view;

import com.yundong.milk.model.BaseReceiveBean;

/**
 * Created by MingweiLi on 2017/3/8.
 */

public interface IApplyForModifyView {
    void applyForModify(BaseReceiveBean baseReceiveBean);
    void applyForModifyOnError(String e);
}
