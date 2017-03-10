package com.yundong.milk.model;

import java.util.ArrayList;

/**
 * Created by MingweiLi on 2017/3/8.
 */

public class MessageInfoBean {
    private String code;
    private String msg;
    private MessageInfoData data;

    @Override
    public String toString() {
        return "MessageInfoBean{" +
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

    public void setData(MessageInfoData data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public MessageInfoData getData() {
        return data;
    }

    public class MessageInfoData{
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
            return "MessageInfoData{" +
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
