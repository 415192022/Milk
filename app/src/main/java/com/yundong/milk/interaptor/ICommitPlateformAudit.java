package com.yundong.milk.interaptor;

import com.yundong.milk.model.BaseReceiveBean;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/3/8.
 */

public interface ICommitPlateformAudit {
    Observable<BaseReceiveBean> commitPlateformAudit(
            String user_id
            , String company_name
            , String charge_people
            , String charge_phone
            , String license
            , String province_name
            , String province_id
            , String city_name
            , String city_id
            , String area_name
            , String area_id
            , String area_info
    );
}
