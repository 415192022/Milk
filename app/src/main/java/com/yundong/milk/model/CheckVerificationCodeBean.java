package com.yundong.milk.model;

/**
 * Created by MingweiLi on 2017/2/27.
 */

public class CheckVerificationCodeBean  {
    private String mobile;
    private String msg;

    @Override
    public String toString() {
        return "CheckVerificationCodeBean{" +
                "mobile='" + mobile + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMobile() {

        return mobile;
    }

    public String getMsg() {
        return msg;
    }
}
