package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IModifyNickeNameService;
import com.yundong.milk.api.service.IUpLoadHeadService;
import com.yundong.milk.interaptor.IModifyNacikName;
import com.yundong.milk.interaptor.IUpLoadHead;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public class ModifyNickNameImpl implements IModifyNacikName {

    @Override
    public Observable<BaseReceiveBean> modifyNacikName(String name, String user_id) {
        return RetrofitUtils.getInstance()
                .retrofitCtreate(URLConst.URL_MILK_BASE, IModifyNickeNameService.class)
                .modifyNickeName(name, user_id);
    }
}
