package com.yundong.milk.model;

import java.util.ArrayList;

/**
 * Created by MingweiLi on 2017/3/7.
 * 我的收藏
 */

public class MyCollectionBean {
    private String code;
    private String msg;
    private MyCollectionBeanData data;

    @Override
    public String toString() {
        return "MyCollectionBean{" +
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

    public void setData(MyCollectionBeanData data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public MyCollectionBeanData getData() {
        return data;
    }

    public class MyCollectionBeanData {
        private String total_page;
        private String total;
        private String per_page;
        private String current_page;
        private String last_page;
        private String next_page_url;
        private String prev_page_url;
        private String from;
        private String to;
        private ArrayList<MyCollectionBeanDataArray> data;

        @Override
        public String toString() {
            return "MyCollectionBeanData{" +
                    "total_page='" + total_page + '\'' +
                    ", total='" + total + '\'' +
                    ", per_page='" + per_page + '\'' +
                    ", current_page='" + current_page + '\'' +
                    ", last_page='" + last_page + '\'' +
                    ", next_page_url='" + next_page_url + '\'' +
                    ", prev_page_url='" + prev_page_url + '\'' +
                    ", from='" + from + '\'' +
                    ", to='" + to + '\'' +
                    ", data=" + data +
                    '}';
        }

        public void setTotal_page(String total_page) {
            this.total_page = total_page;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public void setPer_page(String per_page) {
            this.per_page = per_page;
        }

        public void setCurrent_page(String current_page) {
            this.current_page = current_page;
        }

        public void setLast_page(String last_page) {
            this.last_page = last_page;
        }

        public void setNext_page_url(String next_page_url) {
            this.next_page_url = next_page_url;
        }

        public void setPrev_page_url(String prev_page_url) {
            this.prev_page_url = prev_page_url;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public void setData(ArrayList<MyCollectionBeanDataArray> data) {
            this.data = data;
        }

        public String getTotal_page() {
            return total_page;
        }

        public String getTotal() {
            return total;
        }

        public String getPer_page() {
            return per_page;
        }

        public String getCurrent_page() {
            return current_page;
        }

        public String getLast_page() {
            return last_page;
        }

        public String getNext_page_url() {
            return next_page_url;
        }

        public String getPrev_page_url() {
            return prev_page_url;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public ArrayList<MyCollectionBeanDataArray> getData() {
            return data;
        }

        public class MyCollectionBeanDataArray {
            private String id;
            private String goods_id;
            private String user_id;
            private String add_time;
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
            private String goods_image;
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
            private String update_time;

            @Override
            public String toString() {
                return "MyCollectionBeanDataArray{" +
                        "id='" + id + '\'' +
                        ", goods_id='" + goods_id + '\'' +
                        ", user_id='" + user_id + '\'' +
                        ", add_time='" + add_time + '\'' +
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
                        ", goods_image='" + goods_image + '\'' +
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
                        ", update_time='" + update_time + '\'' +
                        '}';
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
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

            public void setGoods_image(String goods_image) {
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

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public String getId() {
                return id;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public String getAdd_time() {
                return add_time;
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

            public String getGoods_image() {
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

            public String getUpdate_time() {
                return update_time;
            }
        }
    }
}
