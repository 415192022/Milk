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
            , String area
            , String area_id
            , String charge_people
            , String charge_phone
            , String license
    );
}
