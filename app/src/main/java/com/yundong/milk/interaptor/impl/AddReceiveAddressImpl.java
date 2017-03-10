package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IAddReceiveAddressService;
import com.yundong.milk.api.service.ICommitPlateformAuditService;
import com.yundong.milk.api.service.IDeleteCarService;
import com.yundong.milk.interaptor.IAddReceiveAddress;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.util.RetrofitUtils;

import retrofit2.http.Field;
import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/10.
 */

public class AddReceiveAddressImpl implements IAddReceiveAddress {
    @Override
    public Observable<BaseReceiveBean> addReceiveAddress(
            String user_id
            , String phone
            , String uname
            , String province_name
            , String province_id
            , String city_name
            , String city_id
            , String area_name
            , String area_id
            , String area_info) {
        return RetrofitUtils.
                getInstance().
                retrofitCtreate(URLConst.URL_MILK_BASE, IAddReceiveAddressService.class).
                addReceiveAddress(user_id, phone, uname, province_name, province_id, city_name, city_id, area_name, area_id, area_info);
    }
}
