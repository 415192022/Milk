package com.yundong.milk.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MingweiLi on 2017/3/7.
 */

public class GoodsCommentListBean {
    private String code;
    private String msg;
    private GoodsCommentListData data;

    @Override
    public String toString() {
        return "GoodsCommentListBean{" +
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

    public void setData(GoodsCommentListData data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public GoodsCommentListData getData() {
        return data;
    }

    public class GoodsCommentListData {
        private String total_page;
        private String total;
        private String per_page;
        private String current_page;
        private String last_page;
        private String next_page_url;
        private String prev_page_url;
        private String from;
        private String to;
        private ArrayList<GoodsCommentListDataArray> data;

        @Override
        public String toString() {
            return "GoodsCommentListData{" +
                    "data=" + data +
                    ", to='" + to + '\'' +
                    ", from='" + from + '\'' +
                    ", prev_page_url='" + prev_page_url + '\'' +
                    ", next_page_url='" + next_page_url + '\'' +
                    ", last_page='" + last_page + '\'' +
                    ", current_page='" + current_page + '\'' +
                    ", per_page='" + per_page + '\'' +
                    ", total='" + total + '\'' +
                    ", total_page='" + total_page + '\'' +
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

        public void setData(ArrayList<GoodsCommentListDataArray> data) {
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

        public ArrayList<GoodsCommentListDataArray> getData() {
            return data;
        }

        public class GoodsCommentListDataArray {
            private String comment_frommemberid;
            private String comment_frommembername;
            private String comment_addtime;
            private String comment_content;
            private String avatar;

            @Override
            public String toString() {
                return "GoodsCommentListDataArray{" +
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
