package com.yundong.milk.view;

import com.yundong.milk.model.BaseReceiveBean;

/**
 * Created by MingweiLi on 2017/3/3.
 */

public interface IUploadHeadView {
    void upLoadHead(BaseReceiveBean baseReceiveBean);
    void upLoadHeadOnError(String e);
}
