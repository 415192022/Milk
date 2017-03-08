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
        private String id;
        private String company_name;
        private String area;
        private String charge_people;
        private String charge_phone;
        private String license;

        @Override
        public String toString() {
            return "PlatformAuditData{" +
                    "id='" + id + '\'' +
                    ", company_name='" + company_name + '\'' +
                    ", area='" + area + '\'' +
                    ", charge_people='" + charge_people + '\'' +
                    ", charge_phone='" + charge_phone + '\'' +
                    ", license='" + license + '\'' +
                    '}';
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public void setArea(String area) {
            this.area = area;
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

        public String getId() {
            return id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public String getArea() {
            return area;
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
    }
}
