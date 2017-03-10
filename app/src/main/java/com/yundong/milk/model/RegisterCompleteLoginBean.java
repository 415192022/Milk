package com.yundong.milk.model;

/**
 * Created by MingweiLi on 2017/3/10.
 */

public class RegisterCompleteLoginBean {
    private RegistBean registBean;
    private String pwd;


    @Override
    public String toString() {
        return "RegisterCompleteLoginBean{" +
                "registBean=" + registBean +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public void setRegistBean(RegistBean registBean) {
        this.registBean = registBean;
    }

    public RegistBean getRegistBean() {
        return registBean;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    public String getPwd() {
        return pwd;
    }
}
