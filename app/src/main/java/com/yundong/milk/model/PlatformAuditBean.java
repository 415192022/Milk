package com.yundong.milk.model;

/**
 * Created by MingweiLi on 2017/3/8.
 */

public class PlatformAuditBean {
    private String code;
    private String msg;
    private PlatformAuditData data;

    @Override
    public String toString() {
        return "PlatformAuditBean{" +
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

    public void setData(PlatformAuditData data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public PlatformAuditData getData() {
        return data;
    }

    public class PlatformAuditData{
        /**
         * company_id : 3
         * user_id : 8
         * company_name : 公司名称
         * charge_people : 负责人
         * charge_phone : 负责人联系电话
         * license : http://ogqd71wd5.bkt.clouddn.com/FkmrmUtlPlmTPJAd5HjYU-qXi5vK
         * province_name : 贵州省
         * province_id : 24
         * city_name : 六盘水市
         * city_id : 407
         * area_name : 盘县
         * area_id : 4402
         * area_info : 详细地址
         * apply_edit : 2
         * company_state : 1
         * add_time : 1489045266
         */
        private String company_id;
        private String user_id;
        private String company_name;
        private String charge_people;
        private String charge_phone;
        private String license;
        private String province_name;
        private String province_id;
        private String city_name;
        private String city_id;
        private String area_name;
        private String area_id;
        private String area_info;
        private String apply_edit;
        private String company_state;
        private String add_time;

        @Override
        public String toString() {
            return "PlatformAuditData{" +
                    "company_id=" + company_id +
                    ", user_id=" + user_id +
                    ", company_name='" + company_name + '\'' +
                    ", charge_people='" + charge_people + '\'' +
                    ", charge_phone='" + charge_phone + '\'' +
                    ", license='" + license + '\'' +
                    ", province_name='" + province_name + '\'' +
                    ", province_id=" + province_id +
                    ", city_name='" + city_name + '\'' +
                    ", city_id=" + city_id +
                    ", area_name='" + area_name + '\'' +
                    ", area_id=" + area_id +
                    ", area_info='" + area_info + '\'' +
                    ", apply_edit=" + apply_edit +
                    ", company_state=" + company_state +
                    ", add_time=" + add_time +
                    '}';
        }

        public void setCompany_id(String company_id) {
            this.company_id = company_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public void setCharge_people(String charge_people) {
            this.charge_people = charge_people;
        }

        public void setCharge_phone(String charge_phone) {
            this.charge_phone = charge_phone;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public void setArea_info(String area_info) {
            this.area_info = area_info;
        }

        public void setApply_edit(String apply_edit) {
            this.apply_edit = apply_edit;
        }

        public void setCompany_state(String company_state) {
            this.company_state = company_state;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getCompany_id() {
            return company_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public String getCharge_people() {
            return charge_people;
        }

        public String getCharge_phone() {
            return charge_phone;
        }

        public String getLicense() {
            return license;
        }

        public String getProvince_name() {
            return province_name;
        }

        public String getProvince_id() {
            return province_id;
        }

        public String getCity_name() {
            return city_name;
        }

        public String getCity_id() {
            return city_id;
        }

        public String getArea_name() {
            return area_name;
        }

        public String getArea_id() {
            return area_id;
        }

        public String getArea_info() {
            return area_info;
        }

        public String getApply_edit() {
            return apply_edit;
        }

        public String getCompany_state() {
            return company_state;
        }

        public String getAdd_time() {
            return add_time;
        }
    }
}
