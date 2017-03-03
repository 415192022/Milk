package com.yundong.milk.view;

import com.yundong.milk.model.BaseReceivesBean;
import com.yundong.milk.model.TypeBrandBean;

/**
 * Created by MingweiLi on 2017/3/1.
 */

public interface ITypeBrandView {
    void getTypeBrand(TypeBrandBean typeBrandBean);
    void getTypeBrandOnError(String e);
}
