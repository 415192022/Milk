package com.yundong.milk.model;

/**
 * Created by MingweiLi on 2017/3/3.
 */

public class RegistBean {
    private String code;
    private String msg;
    private String data;

    @Override
    public String toString() {
        return "RegistBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getData() {
        return data;
    }

    public class RegistData {
        private String id;
        private String uname;
        private String avatar;
        private String phone;

        @Override
        public String toString() {
            return "RegistData{" +
                    "id='" + id + '\'' +
                    ", uname='" + uname + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getId() {
            return id;
        }

        public String getUname() {
            return uname;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getPhone() {
            return phone;
        }
    }
}
