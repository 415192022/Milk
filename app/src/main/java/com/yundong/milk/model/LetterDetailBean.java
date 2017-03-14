package com.yundong.milk.model;

/**
 * Created by MingweiLi on 2017/3/13.
 */

public class LetterDetailBean {

    /**
     * code : 2000
     * msg : 请求成功
     * data : {"article_id":18,"ac_id":7,"article_url":"豆腐干豆腐","article_img":null,"article_show":1,"article_sort":1,"article_title":"风格的风格","article_content":"<p>这里是原始的textarea中大概的内容，可以从数据中读取<\/p>","article_time":1474677438}
     */

    private String code;
    private String msg;
    private LetterDetail data;

    @Override
    public String toString() {
        return "LetterDetailBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

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

    public LetterDetail getData() {
        return data;
    }

    public void setData(LetterDetail data) {
        this.data = data;
    }

    public static class LetterDetail {
        /**
         * article_id : 18
         * ac_id : 7
         * article_url : 豆腐干豆腐
         * article_img : null
         * article_show : 1
         * article_sort : 1
         * article_title : 风格的风格
         * article_content : <p>这里是原始的textarea中大概的内容，可以从数据中读取</p>
         * article_time : 1474677438
         */

        private int article_id;
        private int ac_id;
        private String article_url;
        private Object article_img;
        private int article_show;
        private int article_sort;
        private String article_title;
        private String article_content;
        private int article_time;


        @Override
        public String toString() {
            return "LetterDetail{" +
                    "article_id=" + article_id +
                    ", ac_id=" + ac_id +
                    ", article_url='" + article_url + '\'' +
                    ", article_img=" + article_img +
                    ", article_show=" + article_show +
                    ", article_sort=" + article_sort +
                    ", article_title='" + article_title + '\'' +
                    ", article_content='" + article_content + '\'' +
                    ", article_time=" + article_time +
                    '}';
        }

        public int getArticle_id() {
            return article_id;
        }

        public void setArticle_id(int article_id) {
            this.article_id = article_id;
        }

        public int getAc_id() {
            return ac_id;
        }

        public void setAc_id(int ac_id) {
            this.ac_id = ac_id;
        }

        public String getArticle_url() {
            return article_url;
        }

        public void setArticle_url(String article_url) {
            this.article_url = article_url;
        }

        public Object getArticle_img() {
            return article_img;
        }

        public void setArticle_img(Object article_img) {
            this.article_img = article_img;
        }

        public int getArticle_show() {
            return article_show;
        }

        public void setArticle_show(int article_show) {
            this.article_show = article_show;
        }

        public int getArticle_sort() {
            return article_sort;
        }

        public void setArticle_sort(int article_sort) {
            this.article_sort = article_sort;
        }

        public String getArticle_title() {
            return article_title;
        }

        public void setArticle_title(String article_title) {
            this.article_title = article_title;
        }

        public String getArticle_content() {
            return article_content;
        }

        public void setArticle_content(String article_content) {
            this.article_content = article_content;
        }

        public int getArticle_time() {
            return article_time;
        }

        public void setArticle_time(int article_time) {
            this.article_time = article_time;
        }
    }
}
