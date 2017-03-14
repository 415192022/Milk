package com.yundong.milk.model;

/**
 * Created by MingweiLi on 2017/3/7.
 */

public class ReceiveGoodsAddressBean {
    private String code;
    private String msg;
    private ReceiveGoodsAddressData data;

    @Override
    public String toString() {
        return "ReceiveGoodsAddressBean{" +
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

    public void setData(ReceiveGoodsAddressData data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ReceiveGoodsAddressData getData() {
        return data;
    }

    public class ReceiveGoodsAddressData {

        /**
         * address_id : 19
         * uname : 13611111111
         * phone : 13611111111
         * province_name : 河北省
         * city_name : 唐山市
         * area_name : 丰润区
         * area_info : Jxjxjxjjxjxjxjx
         * is_apply : 1
         */

        private String address_id;
        private String uname;
        private String phone;
        private String province_name;
        private String city_name;
        private String area_name;
        private String area_info;
        private String is_apply;

        @Override
        public String toString() {
            return "ReceiveGoodsAddressData{" +
                    "address_id=" + address_id +
                    ", uname='" + uname + '\'' +
                    ", phone='" + phone + '\'' +
                    ", province_name='" + province_name + '\'' +
                    ", city_name='" + city_name + '\'' +
                    ", area_name='" + area_name + '\'' +
                    ", area_info='" + area_info + '\'' +
                    ", is_apply=" + is_apply +
                    '}';
        }

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public String getArea_info() {
            return area_info;
        }

        public void setArea_info(String area_info) {
            this.area_info = area_info;
        }

        public String getIs_apply() {
            return is_apply;
        }

        public void setIs_apply(String is_apply) {
            this.is_apply = is_apply;
        }
    }
}
