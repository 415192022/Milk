package com.yundong.milk.model;

import java.util.ArrayList;

/**
 * Created by MingweiLi on 2017/3/8.
 */

public class MessageListBean {
    private String code;
    private String msg;
    private MessageListData data;

    @Override
    public String toString() {
        return "MessageListBean{" +
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

    public void setData(MessageListData data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public MessageListData getData() {
        return data;
    }

    public class MessageListData {
        private String total_page;
        private String total;
        private String per_page;
        private String current_page;
        private String last_page;
        private String next_page_url;
        private String prev_page_url;
        private String from;
        private String to;
        private ArrayList<MessageListDataArray> data;

        @Override
        public String toString() {
            return "MessageListData{" +
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

        public void setData(ArrayList<MessageListDataArray> data) {
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

        public ArrayList<MessageListDataArray> getData() {
            return data;
        }

        public class MessageListDataArray {
            private String message_id;
            private String message_type;
            private String message_title;
            private String message_author;
            private String message_content;
            private String accept_id;
            private String is_see;
            private String send_time;

            @Override
            public String toString() {
                return "MessageListDataArray{" +
                        "message_id='" + message_id + '\'' +
                        ", message_type='" + message_type + '\'' +
                        ", message_title='" + message_title + '\'' +
                        ", message_author='" + message_author + '\'' +
                        ", message_content='" + message_content + '\'' +
                        ", accept_id='" + accept_id + '\'' +
                        ", is_see='" + is_see + '\'' +
                        ", send_time='" + send_time + '\'' +
                        '}';
            }

            public void setMessage_id(String message_id) {
                this.message_id = message_id;
            }

            public void setMessage_type(String message_type) {
                this.message_type = message_type;
            }

            public void setMessage_title(String message_title) {
                this.message_title = message_title;
            }

            public void setMessage_author(String message_author) {
                this.message_author = message_author;
            }

            public void setMessage_content(String message_content) {
                this.message_content = message_content;
            }

            public void setAccept_id(String accept_id) {
                this.accept_id = accept_id;
            }

            public void setIs_see(String is_see) {
                this.is_see = is_see;
            }

            public void setSend_time(String send_time) {
                this.send_time = send_time;
            }

            public String getMessage_id() {
                return message_id;
            }

            public String getMessage_type() {
                return message_type;
            }

            public String getMessage_title() {
                return message_title;
            }

            public String getMessage_author() {
                return message_author;
            }

            public String getMessage_content() {
                return message_content;
            }

            public String getAccept_id() {
                return accept_id;
            }

            public String getIs_see() {
                return is_see;
            }

            public String getSend_time() {
                return send_time;
            }
        }
    }
}
