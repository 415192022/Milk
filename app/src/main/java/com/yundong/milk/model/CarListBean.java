package com.yundong.milk.model;

import java.util.ArrayList;

/**
 * Created by MingweiLi on 2017/2/27.
 */

public class CarListBean {
    private ArrayList<CarListDataA> data;
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

    public void setDatas(ArrayList<CarListDataA> datas) {
        this.data = datas;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<CarListDataA> getDatas() {

        return data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public class CarListDataA{
        private String goods_id;
        private String goods_name;
        private String goods_price;
        private String goods_marketprice;
        private String goods_main_image;
        private String id;
        private String user_id;
        private String number;
        private String add_time;

        @Override
        public String toString() {
            return "CarListDataA{" +
                    "goods_id='" + goods_id + '\'' +
                    ", goods_name='" + goods_name + '\'' +
                    ", goods_price='" + goods_price + '\'' +
                    ", goods_marketprice='" + goods_marketprice + '\'' +
                    ", goods_main_image='" + goods_main_image + '\'' +
                    ", id='" + id + '\'' +
                    ", user_id='" + user_id + '\'' +
                    ", number='" + number + '\'' +
                    ", add_time='" + add_time + '\'' +
                    '}';
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public void setGoods_marketprice(String goods_marketprice) {
            this.goods_marketprice = goods_marketprice;
        }

        public void setGoods_main_image(String goods_main_image) {
            this.goods_main_image = goods_main_image;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public String getGoods_marketprice() {
            return goods_marketprice;
        }

        public String getGoods_main_image() {
            return goods_main_image;
        }

        public String getId() {
            return id;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getNumber() {
            return number;
        }

        public String getAdd_time() {
            return add_time;
        }
    }
}
