package com.yundong.milk.model;

import java.util.ArrayList;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public class RefundBean {
    private String code;
    private String msg;
    private RefundData data;

    @Override
    public String toString() {
        return "RefundBean{" +
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

    public void setData(RefundData data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public RefundData getData() {
        return data;
    }

    public class RefundData {
        private String total_page;
        private String total;
        private String per_page;
        private String current_page;
        private String last_page;
        private String next_page_url;
        private String prev_page_url;
        private String from;
        private String to;
        private ArrayList<RefundArray> data;

        @Override
        public String toString() {
            return "RefundData{" +
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

        public void setData(ArrayList<RefundArray> data) {
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

        public ArrayList<RefundArray> getData() {
            return data;
        }

        public class RefundArray {
            private String order_id;
            private String order_sn;
            private String goods_id;
            private String goods_sum;
            private String order_amount;
            private String order_state;
            private String service_state;
            private String is_comment;
            private String create_time;
            private String payment_time;
            private String finished_time;
            private String goods_name;
            private String goods_main_image;
            private String goods_price;
            private String goods_marketprice;
            private String return_id;
            private String user_id;
            private String return_money;
            private String return_reason;
            private String isagree;
            private String adminrefuse;
            private String return_state;
            private String return_addtime;

            @Override
            public String toString() {
                return "RefundArray{" +
                        "order_id='" + order_id + '\'' +
                        ", order_sn='" + order_sn + '\'' +
                        ", goods_id='" + goods_id + '\'' +
                        ", goods_sum='" + goods_sum + '\'' +
                        ", order_amount='" + order_amount + '\'' +
                        ", order_state='" + order_state + '\'' +
                        ", service_state='" + service_state + '\'' +
                        ", is_comment='" + is_comment + '\'' +
                        ", create_time='" + create_time + '\'' +
                        ", payment_time='" + payment_time + '\'' +
                        ", finished_time='" + finished_time + '\'' +
                        ", goods_name='" + goods_name + '\'' +
                        ", goods_main_image='" + goods_main_image + '\'' +
                        ", goods_price='" + goods_price + '\'' +
                        ", goods_marketprice='" + goods_marketprice + '\'' +
                        ", return_id='" + return_id + '\'' +
                        ", user_id='" + user_id + '\'' +
                        ", return_money='" + return_money + '\'' +
                        ", return_reason='" + return_reason + '\'' +
                        ", isagree='" + isagree + '\'' +
                        ", adminrefuse='" + adminrefuse + '\'' +
                        ", return_state='" + return_state + '\'' +
                        ", return_addtime='" + return_addtime + '\'' +
                        '}';
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public void setOrder_sn(String order_sn) {
                this.order_sn = order_sn;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public void setGoods_sum(String goods_sum) {
                this.goods_sum = goods_sum;
            }

            public void setOrder_amount(String order_amount) {
                this.order_amount = order_amount;
            }

            public void setOrder_state(String order_state) {
                this.order_state = order_state;
            }

            public void setService_state(String service_state) {
                this.service_state = service_state;
            }

            public void setIs_comment(String is_comment) {
                this.is_comment = is_comment;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public void setPayment_time(String payment_time) {
                this.payment_time = payment_time;
            }

            public void setFinished_time(String finished_time) {
                this.finished_time = finished_time;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public void setGoods_main_image(String goods_main_image) {
                this.goods_main_image = goods_main_image;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public void setGoods_marketprice(String goods_marketprice) {
                this.goods_marketprice = goods_marketprice;
            }

            public void setReturn_id(String return_id) {
                this.return_id = return_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public void setReturn_money(String return_money) {
                this.return_money = return_money;
            }

            public void setReturn_reason(String return_reason) {
                this.return_reason = return_reason;
            }

            public void setIsagree(String isagree) {
                this.isagree = isagree;
            }

            public void setAdminrefuse(String adminrefuse) {
                this.adminrefuse = adminrefuse;
            }

            public void setReturn_state(String return_state) {
                this.return_state = return_state;
            }

            public void setReturn_addtime(String return_addtime) {
                this.return_addtime = return_addtime;
            }

            public String getOrder_id() {
                return order_id;
            }

            public String getOrder_sn() {
                return order_sn;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public String getGoods_sum() {
                return goods_sum;
            }

            public String getOrder_amount() {
                return order_amount;
            }

            public String getOrder_state() {
                return order_state;
            }

            public String getService_state() {
                return service_state;
            }

            public String getIs_comment() {
                return is_comment;
            }

            public String getCreate_time() {
                return create_time;
            }

            public String getPayment_time() {
                return payment_time;
            }

            public String getFinished_time() {
                return finished_time;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public String getGoods_main_image() {
                return goods_main_image;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public String getGoods_marketprice() {
                return goods_marketprice;
            }

            public String getReturn_id() {
                return return_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public String getReturn_money() {
                return return_money;
            }

            public String getReturn_reason() {
                return return_reason;
            }

            public String getIsagree() {
                return isagree;
            }

            public String getAdminrefuse() {
                return adminrefuse;
            }

            public String getReturn_state() {
                return return_state;
            }

            public String getReturn_addtime() {
                return return_addtime;
            }
        }

    }
}
