package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public interface IAddReceiveAddress {
    Observable<BaseReceiveBean> addReceiveAddress(
            String user_id,
            String phone,
            String uname,
            String province_name,
            String province_id,
            String city_name,
            String city_id,
            String area_name,
            String area_id,
            String area_info
    );
}
