package com.yundong.milk.view;

import com.yundong.milk.model.BaseReceiveBean;

/**
 * Created by MingweiLi on 2017/2/27.
 */

public interface ICheckVerificationView {
    void checkVerificationCode(BaseReceiveBean baseReceiveBean);
    void checkVerificationCodeOnError(String e);
}
