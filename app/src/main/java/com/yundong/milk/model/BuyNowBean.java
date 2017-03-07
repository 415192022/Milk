package com.yundong.milk.model;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public class BuyNowBean {
    private String code;
    private String msg;
    private BuyNowData data;

    @Override
    public String toString() {
        return "BuyNowBean{" +
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

    public void setData(BuyNowData data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public BuyNowData getData() {
        return data;
    }

    public class BuyNowData {
        private String order_id;

        @Override
        public String toString() {
            return "BuyNowData{" +
                    "order_id='" + order_id + '\'' +
                    '}';
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_id() {
            return order_id;
        }
    }
}
