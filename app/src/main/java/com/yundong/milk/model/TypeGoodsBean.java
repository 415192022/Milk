package com.yundong.milk.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MingweiLi on 2017/3/1.
 */

public class TypeGoodsBean {
    private String code;
    private String msg;
    private TypeGoodsBeanDataO data;

    @Override
    public String toString() {
        return "TypeGoodsBean{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(TypeGoodsBeanDataO data) {
        this.data = data;
    }

    public String getCode() {

        return code;
    }

    public String getMsg() {
        return msg;
    }

    public TypeGoodsBeanDataO getData() {
        return data;
    }

    public class TypeGoodsBeanDataO {
        private String total;
        private String per_page;
        private String current_page;
        private String last_page;
        private String next_page_url;
        private String prev_page_url;
        private String from;
        private String to;
        private ArrayList<TypeGoodsBeanDataA> data;

        @Override
        public String toString() {
            return "TypeGoodsBeanDataO{" +
                    "total='" + total + '\'' +
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

        public void setData(ArrayList<TypeGoodsBeanDataA> data) {
            this.data = data;
        }

        public ArrayList<TypeGoodsBeanDataA> getTypeGoodsBeanDataAData() {
            return data;
        }

        public class TypeGoodsBeanDataA {
            String goods_id;
            String goods_name;
            String goods_price;
            String goods_marketprice;
            String goods_image;


            @Override
            public String toString() {
                return "TypeGoodsBeanDataA{" +
                        "goods_id='" + goods_id + '\'' +
                        ", goods_name='" + goods_name + '\'' +
                        ", goods_price='" + goods_price + '\'' +
                        ", goods_marketprice='" + goods_marketprice + '\'' +
                        ", goods_image='" + goods_image + '\'' +
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

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
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

            public String getGoods_image() {
                return goods_image;
            }
        }
    }
}
