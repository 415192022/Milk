package com.yundong.milk.model;

import java.util.ArrayList;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public class LettersBean  {
    private String code;
    private String msg;
    private LettersDataO data;

    @Override
    public String toString() {
        return "LettersBean{" +
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

    public void setData(LettersDataO data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public LettersDataO getData() {
        return data;
    }

    public class LettersDataO{
        private String total_page;
        private String total;
        private String per_page;
        private String current_page;
        private String last_page;
        private String next_page_url;
        private String prev_page_url;
        private String from;
        private String to;
        private ArrayList<LettersDataA> data;

        @Override
        public String toString() {
            return "LettersDataO{" +
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

        public void setData(ArrayList<LettersDataA> data) {
            this.data = data;
        }

        public ArrayList<LettersDataA> getData() {
            return data;
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


        public class LettersDataA{
            private String article_id;
            private String article_title;
            private String article_img;

            @Override
            public String toString() {
                return "LettersDataA{" +
                        "article_id='" + article_id + '\'' +
                        ", article_title='" + article_title + '\'' +
                        ", article_img='" + article_img + '\'' +
                        '}';
            }

            public void setArticle_id(String article_id) {
                this.article_id = article_id;
            }

            public void setArticle_title(String article_title) {
                this.article_title = article_title;
            }

            public void setArticle_img(String article_img) {
                this.article_img = article_img;
            }

            public String getArticle_id() {
                return article_id;
            }

            public String getArticle_title() {
                return article_title;
            }

            public String getArticle_img() {
                return article_img;
            }
        }
    }

}
