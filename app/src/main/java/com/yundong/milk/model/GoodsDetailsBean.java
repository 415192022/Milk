package com.yundong.milk.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MingweiLi on 2017/3/2.
 */

public class GoodsDetailsBean implements Serializable {

    /**
     * code : 2000
     * msg : 请求成功
     * data : {"goods_id":1,"goods_type":1,"goods_type_name":"常温牛奶","brand_id":2,"brand_name":"伊利","goods_spec":43543,"goods_name":"别的非官方","goods_describe":null,"goods_text":"<p>体育活动如果法国恢复国家规划局官方公会激光焊接国际新城女不女冰女吧<\/p>","goods_sum":14,"goods_price":"453534.00","goods_marketprice":"65465.00","Recommend":1,"goods_salenum":0,"goods_collect":28,"goods_main_image":"http://ogqd71wd5.bkt.clouddn.com/2f8f5d5293fb42037e06c2228d65e076.jpg","goods_image":["http://ogqd71wd5.bkt.clouddn.com/2f8f5d5293fb42037e06c2228d65e076.jpg","http://ogqd71wd5.bkt.clouddn.com/2b868d00b0968a699d32409c91274efa.jpg","http://ogqd71wd5.bkt.clouddn.com/0f3c0802dd2143411ba2051a8b720dc5.jpg"],"province_id":null,"area_id":98,"place_of_origin":null,"features":null,"batching":null,"net_content":null,"shelf_life":null,"is_check":0,"goods_state":1,"goods_stateremark":null,"send_city":null,"add_time":"2017-03-14 13:35:33","update_time":"2017-02-07 04:17:43","comment":{"comment_frommemberid":12,"comment_frommembername":"Ggg","comment_addtime":1489397920,"comment_content":"民工","avatar":"http://ogqd71wd5.bkt.clouddn.com/FgLldADla5nZpD-_YQtkpjuPIBk_"},"comment_sum":6}
     */

    private String code;
    private String msg;
    private GoodsDetailsData data;

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

    public GoodsDetailsData getData() {
        return data;
    }

    public void setData(GoodsDetailsData data) {
        this.data = data;
    }

    public static class GoodsDetailsData {

        private String goods_id;
        private String goods_type;
        private String goods_type_name;
        private String brand_id;
        private String brand_name;
        private String goods_spec;
        private String goods_name;
        private String goods_describe;
        private String goods_text;
        private String goods_sum;
        private String goods_price;
        private String goods_marketprice;
        private String Recommend;
        private String goods_salenum;
        private String goods_collect;
        private String goods_main_image;
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
        private CommentBean comment;
        private String comment_sum;
        private List<String> goods_image;

        @Override
        public String toString() {
            return "GoodsDetailsData{" +
                    "goods_id='" + goods_id + '\'' +
                    ", goods_type='" + goods_type + '\'' +
                    ", goods_type_name='" + goods_type_name + '\'' +
                    ", brand_id='" + brand_id + '\'' +
                    ", brand_name='" + brand_name + '\'' +
                    ", goods_spec='" + goods_spec + '\'' +
                    ", goods_name='" + goods_name + '\'' +
                    ", goods_describe='" + goods_describe + '\'' +
                    ", goods_text='" + goods_text + '\'' +
                    ", goods_sum='" + goods_sum + '\'' +
                    ", goods_price='" + goods_price + '\'' +
                    ", goods_marketprice='" + goods_marketprice + '\'' +
                    ", Recommend='" + Recommend + '\'' +
                    ", goods_salenum='" + goods_salenum + '\'' +
                    ", goods_collect='" + goods_collect + '\'' +
                    ", goods_main_image='" + goods_main_image + '\'' +
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
                    ", goods_image=" + goods_image +
                    '}';
        }

        public void setGoods_image(List<String> goods_image) {
            this.goods_image = goods_image;
        }

        public List<String> getGoods_image() {
            return goods_image;
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

        public void setGoods_describe(String goods_describe) {
            this.goods_describe = goods_describe;
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

        public void setComment(CommentBean comment) {
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

        public String getGoods_describe() {
            return goods_describe;
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

        public CommentBean getComment() {
            return comment;
        }

        public String getComment_sum() {
            return comment_sum;
        }

        public static class CommentBean {
            /**
             * comment_frommemberid : 12
             * comment_frommembername : Ggg
             * comment_addtime : 1489397920
             * comment_content : 民工
             * avatar : http://ogqd71wd5.bkt.clouddn.com/FgLldADla5nZpD-_YQtkpjuPIBk_
             */

            private String comment_frommemberid;
            private String comment_frommembername;
            private String comment_addtime;
            private String comment_content;
            private String avatar;

            @Override
            public String toString() {
                return "CommentBean{" +
                        "comment_frommemberid='" + comment_frommemberid + '\'' +
                        ", comment_frommembername='" + comment_frommembername + '\'' +
                        ", comment_addtime='" + comment_addtime + '\'' +
                        ", comment_content='" + comment_content + '\'' +
                        ", avatar='" + avatar + '\'' +
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

            public void setAvatar(String avatar) {
                this.avatar = avatar;
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

            public String getAvatar() {
                return avatar;
            }
        }
    }
}
