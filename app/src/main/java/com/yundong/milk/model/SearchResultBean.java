package com.yundong.milk.model;

import java.util.List;

/**
 * Created by MingweiLi on 2017/3/14.
 */

public class SearchResultBean {

    /**
     * code : 2000
     * msg : 请求成功
     * data : {"total_page":2,"total":2,"per_page":1,"current_page":1,"last_page":2,"next_page_url":"http://115.29.246.88:8889/goods/search?page=2","prev_page_url":null,"from":1,"to":1,"data":[{"goods_id":2,"goods_name":"吼吼人人","goods_price":"234.00","goods_marketprice":"423423.00","goods_main_image":"http://ogqd71wd5.bkt.clouddn.com/2b868d00b0968a699d32409c91274efa.jpg","goods_salenum":0}]}
     */

    private String code;
    private String msg;
    private SearchResultData data;

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

    public SearchResultData getData() {
        return data;
    }

    public void setData(SearchResultData data) {
        this.data = data;
    }

    public static class SearchResultData {
        /**
         * total_page : 2
         * total : 2
         * per_page : 1
         * current_page : 1
         * last_page : 2
         * next_page_url : http://115.29.246.88:8889/goods/search?page=2
         * prev_page_url : null
         * from : 1
         * to : 1
         * data : [{"goods_id":2,"goods_name":"吼吼人人","goods_price":"234.00","goods_marketprice":"423423.00","goods_main_image":"http://ogqd71wd5.bkt.clouddn.com/2b868d00b0968a699d32409c91274efa.jpg","goods_salenum":0}]
         */

        private String total_page;
        private String total;
        private String per_page;
        private String current_page;
        private String last_page;
        private String next_page_url;
        private String prev_page_url;
        private String from;
        private String to;
        private List<SearchResultArray> data;

        @Override
        public String toString() {
            return "SearchResultData{" +
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

        public void setData(List<SearchResultArray> data) {
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

        public List<SearchResultArray> getData() {
            return data;
        }

        public static class SearchResultArray {
            /**
             * goods_id : 2
             * goods_name : 吼吼人人
             * goods_price : 234.00
             * goods_marketprice : 423423.00
             * goods_main_image : http://ogqd71wd5.bkt.clouddn.com/2b868d00b0968a699d32409c91274efa.jpg
             * goods_salenum : 0
             */

            private String goods_id;
            private String goods_name;
            private String goods_price;
            private String goods_marketprice;
            private String goods_main_image;
            private String goods_salenum;

            @Override
            public String toString() {
                return "SearchResultArray{" +
                        "goods_salenum='" + goods_salenum + '\'' +
                        ", goods_main_image='" + goods_main_image + '\'' +
                        ", goods_marketprice='" + goods_marketprice + '\'' +
                        ", goods_price='" + goods_price + '\'' +
                        ", goods_name='" + goods_name + '\'' +
                        ", goods_id='" + goods_id + '\'' +
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

            public void setGoods_salenum(String goods_salenum) {
                this.goods_salenum = goods_salenum;
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

            public String getGoods_salenum() {
                return goods_salenum;
            }
        }
    }
}
