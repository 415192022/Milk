package com.yundong.milk.model;

import java.util.List;

/**
 * Created by MingweiLi on 2017/2/27.
 */

public class BaseReceiveBean<T> {
    private String code;
    private String msg;
    private T data;


    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {

        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "BaseReceiveBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
