package com.yundong.milk.view;

import com.yundong.milk.model.BaseReceiveBean;

/**
 * Created by MingweiLi on 2017/3/14.
 */

public interface IApplyForChangeCompanyView {
    void applyForChangeCompany(BaseReceiveBean baseReceiveBean);
    void applyForChangeCompanyOnError(String e);
}
