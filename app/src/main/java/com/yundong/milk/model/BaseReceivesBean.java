package com.yundong.milk.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public class BaseReceivesBean<T> {
    private ArrayList<T> data;
    private String code;
    private String msg;

    @Override
    public String toString() {
        return "BaseReceivesBean{" +
                "datas=" + data +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public void setDatas(ArrayList<T> datas) {
        this.data = datas;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<T> getDatas() {

        return data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
