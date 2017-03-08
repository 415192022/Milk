package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IMyCollectionDeleteService;
import com.yundong.milk.interaptor.IMyCollectionDelete;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public class MyCollectionDeleteImpl implements IMyCollectionDelete {

    @Override
    public Observable<BaseReceiveBean> deleteMyCollection(String coll_id) {
        return RetrofitUtils.getInstance()
                .retrofitCtreate(URLConst.URL_MILK_BASE, IMyCollectionDeleteService.class)
                .deleteMyCollection(coll_id);
    }
}
