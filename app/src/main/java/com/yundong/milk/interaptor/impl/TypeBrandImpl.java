package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IRegisterService;
import com.yundong.milk.api.service.ITypeBrandService;
import com.yundong.milk.interaptor.ITypeBrand;
import com.yundong.milk.model.BaseReceivesBean;
import com.yundong.milk.model.TypeBrandBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/1.
 */

public class TypeBrandImpl implements ITypeBrand {
    @Override
    public Observable<TypeBrandBean> getTypeBrandBean(String class_id) {
        return RetrofitUtils.
                getInstance().
                retrofitCtreate(URLConst.URL_MILK_BASE, ITypeBrandService.class).getTypeBrand(class_id);
    }
}
