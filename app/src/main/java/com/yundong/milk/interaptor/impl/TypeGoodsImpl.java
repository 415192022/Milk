package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IRegisterService;
import com.yundong.milk.api.service.ITypeBrandService;
import com.yundong.milk.api.service.ITypeGoodsService;
import com.yundong.milk.interaptor.ITypeGoods;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.BaseReceivesBean;
import com.yundong.milk.model.TypeGoodsBean;
import com.yundong.milk.util.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/1.
 */

public class TypeGoodsImpl implements ITypeGoods {
    @Override
    public Observable<TypeGoodsBean> getTypeGoods(String class_id, String brand_id, String page, String page_data) {
        return RetrofitUtils.
                getInstance().
                retrofitCtreate(URLConst.URL_MILK_BASE, ITypeGoodsService.class).getTypeGoods(class_id, brand_id, page, page_data);
    }
}
