package com.yundong.milk.view;

import com.yundong.milk.model.BaseReceiveBean;

/**
 * Created by MingweiLi on 2017/2/27.
 */

public interface IGetVerificationView {
    void getVerificationCode(BaseReceiveBean baseReceiveBean);
    void getVerificationCodeOnError(String e);
}
