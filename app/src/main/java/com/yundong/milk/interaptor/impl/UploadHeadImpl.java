package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IUpLoadHeadService;
import com.yundong.milk.interaptor.IUpLoadHead;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public class UploadHeadImpl implements IUpLoadHead {
    @Override
    public Observable<BaseReceiveBean> upLoadHead(String img, String user_id) {
        return RetrofitUtils.getInstance()
                .retrofitCtreate(URLConst.URL_MILK_BASE, IUpLoadHeadService.class)
                .uploadHead(img, user_id);
    }
}
