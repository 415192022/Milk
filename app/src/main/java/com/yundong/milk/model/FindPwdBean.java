package com.yundong.milk.model;

/**
 * Created by MingweiLi on 2017/3/10.
 */

public class FindPwdBean {

    /**
     * code : 2000
     * msg : 修改成功
     * data : {"id":11,"uname":"RAV","avatar":"http://ogqd71wd5.bkt.clouddn.com/FsyZmd46P4M2mNDpAB_AkW40n_bx","phone":"15502113227"}
     */

    private String code;
    private String msg;
    private FindPwdData data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public FindPwdData getData() {
        return data;
    }

    public void setData(FindPwdData data) {
        this.data = data;
    }

    public static class FindPwdData {
        /**
         * id : 11
         * uname : RAV
         * avatar : http://ogqd71wd5.bkt.clouddn.com/FsyZmd46P4M2mNDpAB_AkW40n_bx
         * phone : 15502113227
         */

        private int id;
        private String uname;
        private String avatar;
        private String phone;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
