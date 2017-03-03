package com.yundong.milk.model;

import java.util.ArrayList;

/**
 * Created by MingweiLi on 2017/3/2.
 */

public class GoodsDetailsBean {
    private String code;
    private String msg;
    private GoodsDetailsDataO data;

    @Override
    public String toString() {
        return "GoodsDetailsBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public void setData(GoodsDetailsDataO data) {
        this.data = data;
    }

    public GoodsDetailsDataO getData() {
        return data;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    public class GoodsDetailsDataO {
        private String goods_id;
        private String goods_type;
        private String goods_type_name;
        private String brand_id;
        private String brand_name;
        private String goods_spec;
        private String goods_name;
        private String goods_text;
        private String goods_sum;
        private String goods_price;
        private String goods_marketprice;
        private String Recommend;
        private String goods_salenum;
        private String goods_collect;
        private String goods_main_image;
        private ArrayList<String> goods_image;
        private String province_id;
        private String area_id;
        private String place_of_origin;
        private String features;
        private String batching;
        private String net_content;
        private String shelf_life;
        private String is_check;
        private String goods_state;
        private String goods_stateremark;
        private String send_city;
        private String add_time;
        private String update_time;
        private Comment comment;
        private String comment_sum;

        @Override
        public String toString() {
            return "GoodsDetailsDataO{" +
                    "goods_id='" + goods_id + '\'' +
                    ", goods_type='" + goods_type + '\'' +
                    ", goods_type_name='" + goods_type_name + '\'' +
                    ", brand_id='" + brand_id + '\'' +
                    ", brand_name='" + brand_name + '\'' +
                    ", goods_spec='" + goods_spec + '\'' +
                    ", goods_name='" + goods_name + '\'' +
                    ", goods_text='" + goods_text + '\'' +
                    ", goods_sum='" + goods_sum + '\'' +
                    ", goods_price='" + goods_price + '\'' +
                    ", goods_marketprice='" + goods_marketprice + '\'' +
                    ", Recommend='" + Recommend + '\'' +
                    ", goods_salenum='" + goods_salenum + '\'' +
                    ", goods_collect='" + goods_collect + '\'' +
                    ", goods_main_image='" + goods_main_image + '\'' +
                    ", goods_image=" + goods_image +
                    ", province_id='" + province_id + '\'' +
                    ", area_id='" + area_id + '\'' +
                    ", place_of_origin='" + place_of_origin + '\'' +
                    ", features='" + features + '\'' +
                    ", batching='" + batching + '\'' +
                    ", net_content='" + net_content + '\'' +
                    ", shelf_life='" + shelf_life + '\'' +
                    ", is_check='" + is_check + '\'' +
                    ", goods_state='" + goods_state + '\'' +
                    ", goods_stateremark='" + goods_stateremark + '\'' +
                    ", send_city='" + send_city + '\'' +
                    ", add_time='" + add_time + '\'' +
                    ", update_time='" + update_time + '\'' +
                    ", comment=" + comment +
                    ", comment_sum='" + comment_sum + '\'' +
                    '}';
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public void setGoods_type(String goods_type) {
            this.goods_type = goods_type;
        }

        public void setGoods_type_name(String goods_type_name) {
            this.goods_type_name = goods_type_name;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public void setGoods_spec(String goods_spec) {
            this.goods_spec = goods_spec;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public void setGoods_text(String goods_text) {
            this.goods_text = goods_text;
        }

        public void setGoods_sum(String goods_sum) {
            this.goods_sum = goods_sum;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public void setGoods_marketprice(String goods_marketprice) {
            this.goods_marketprice = goods_marketprice;
        }

        public void setRecommend(String recommend) {
            Recommend = recommend;
        }

        public void setGoods_salenum(String goods_salenum) {
            this.goods_salenum = goods_salenum;
        }

        public void setGoods_collect(String goods_collect) {
            this.goods_collect = goods_collect;
        }

        public void setGoods_main_image(String goods_main_image) {
            this.goods_main_image = goods_main_image;
        }

        public void setGoods_image(ArrayList<String> goods_image) {
            this.goods_image = goods_image;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public void setPlace_of_origin(String place_of_origin) {
            this.place_of_origin = place_of_origin;
        }

        public void setFeatures(String features) {
            this.features = features;
        }

        public void setBatching(String batching) {
            this.batching = batching;
        }

        public void setNet_content(String net_content) {
            this.net_content = net_content;
        }

        public void setShelf_life(String shelf_life) {
            this.shelf_life = shelf_life;
        }

        public void setIs_check(String is_check) {
            this.is_check = is_check;
        }

        public void setGoods_state(String goods_state) {
            this.goods_state = goods_state;
        }

        public void setGoods_stateremark(String goods_stateremark) {
            this.goods_stateremark = goods_stateremark;
        }

        public void setSend_city(String send_city) {
            this.send_city = send_city;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public void setComment(Comment comment) {
            this.comment = comment;
        }

        public void setComment_sum(String comment_sum) {
            this.comment_sum = comment_sum;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public String getGoods_type() {
            return goods_type;
        }

        public String getGoods_type_name() {
            return goods_type_name;
        }

        public String getBrand_id() {
            return brand_id;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public String getGoods_spec() {
            return goods_spec;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public String getGoods_text() {
            return goods_text;
        }

        public String getGoods_sum() {
            return goods_sum;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public String getGoods_marketprice() {
            return goods_marketprice;
        }

        public String getRecommend() {
            return Recommend;
        }

        public String getGoods_salenum() {
            return goods_salenum;
        }

        public String getGoods_collect() {
            return goods_collect;
        }

        public String getGoods_main_image() {
            return goods_main_image;
        }

        public ArrayList<String> getGoods_image() {
            return goods_image;
        }

        public String getProvince_id() {
            return province_id;
        }

        public String getArea_id() {
            return area_id;
        }

        public String getPlace_of_origin() {
            return place_of_origin;
        }

        public String getFeatures() {
            return features;
        }

        public String getBatching() {
            return batching;
        }

        public String getNet_content() {
            return net_content;
        }

        public String getShelf_life() {
            return shelf_life;
        }

        public String getIs_check() {
            return is_check;
        }

        public String getGoods_state() {
            return goods_state;
        }

        public String getGoods_stateremark() {
            return goods_stateremark;
        }

        public String getSend_city() {
            return send_city;
        }

        public String getAdd_time() {
            return add_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public Comment getComment() {
            return comment;
        }

        public String getComment_sum() {
            return comment_sum;
        }

        public class Comment {
            private String comment_frommemberid;
            private String comment_frommembername;
            private String comment_addtime;
            private String comment_content;

            @Override
            public String toString() {
                return "Comment{" +
                        "comment_frommemberid='" + comment_frommemberid + '\'' +
                        ", comment_frommembername='" + comment_frommembername + '\'' +
                        ", comment_addtime='" + comment_addtime + '\'' +
                        ", comment_content='" + comment_content + '\'' +
                        '}';
            }

            public void setComment_frommemberid(String comment_frommemberid) {
                this.comment_frommemberid = comment_frommemberid;
            }

            public void setComment_frommembername(String comment_frommembername) {
                this.comment_frommembername = comment_frommembername;
            }

            public void setComment_addtime(String comment_addtime) {
                this.comment_addtime = comment_addtime;
            }

            public void setComment_content(String comment_content) {
                this.comment_content = comment_content;
            }

            public String getComment_frommemberid() {
                return comment_frommemberid;
            }

            public String getComment_frommembername() {
                return comment_frommembername;
            }

            public String getComment_addtime() {
                return comment_addtime;
            }

            public String getComment_content() {
                return comment_content;
            }
        }

    }
}
