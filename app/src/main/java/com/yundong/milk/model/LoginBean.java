package com.yundong.milk.model;

/**
 * Created by MingweiLi on 2017/2/27.
 */

public class LoginBean {
    private String code;
    private String msg;
    private LoginData data;

    @Override
    public String toString() {
        return "LoginBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(LoginData data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public LoginData getData() {
        return data;
    }

    public class LoginData {
        private String token;
        private UserInfo userinfo;

        @Override
        public String toString() {
            return "LoginData{" +
                    "token='" + token + '\'' +
                    ", userinfo=" + userinfo +
                    '}';
        }

        public void setToken(String token) {
            this.token = token;
        }

        public void setUserinfo(UserInfo userinfo) {
            this.userinfo = userinfo;
        }

        public String getToken() {
            return token;
        }

        public UserInfo getUserinfo() {
            return userinfo;
        }

        public class UserInfo {
            private String id;
            private String uname;
            private String avatar;
            private String phone;

            @Override
            public String toString() {
                return "UserInfo{" +
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


}
