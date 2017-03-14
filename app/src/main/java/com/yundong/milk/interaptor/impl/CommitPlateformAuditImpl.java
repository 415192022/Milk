package com.yundong.milk.interaptor.impl;

import com.yundong.milk.api.URLConst;
import com.yundong.milk.api.service.IAllTypeService;
import com.yundong.milk.api.service.ICommitPlateformAuditService;
import com.yundong.milk.interaptor.IAllType;
import com.yundong.milk.interaptor.ICommitPlateformAudit;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.RecommentTypeBean;
import com.yundong.milk.util.RetrofitUtils;

import rx.Observable;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public class CommitPlateformAuditImpl implements ICommitPlateformAudit {

    @Override
    public Observable<BaseReceiveBean> commitPlateformAudit(
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
    ) {
        return RetrofitUtils.getInstance()
                .retrofitCtreate(URLConst.URL_MILK_BASE, ICommitPlateformAuditService.class)
                .commitPlateformAudit(user_id, company_name, charge_people, charge_phone, license, province_name, province_id, city_name, city_id, area_name, area_id, area_info);
    }
}
