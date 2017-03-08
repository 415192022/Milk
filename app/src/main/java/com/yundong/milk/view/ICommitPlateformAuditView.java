package com.yundong.milk.view;

import com.yundong.milk.model.BaseReceiveBean;

/**
 * Created by MingweiLi on 2017/3/8.
 */

public interface ICommitPlateformAuditView {
    void commitPlateformAudit(BaseReceiveBean baseReceiveBean);
    void commitPlateformAuditOnError(String e);
}
