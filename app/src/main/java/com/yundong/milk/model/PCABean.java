package com.yundong.milk.model;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.util.List;

/**
 * Created by MingweiLi on 2017/3/10.
 */

public class PCABean {

    /**
     * code : 2000
     * msg : 请求成功
     * data : [{"area_id":73,"area_name":"石家庄市","area_parent_id":3,"area_sort":0,"area_deep":2}]
     */

    private int code;
    private String msg;
    private List<PCAData> data;

    @Override
    public String toString() {
        return "PCABean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<PCAData> getData() {
        return data;
    }

    public void setData(List<PCAData> data) {
        this.data = data;
    }

    public static class PCAData implements IPickerViewData{
        /**
         * area_id : 73
         * area_name : 石家庄市
         * area_parent_id : 3
         * area_sort : 0
         * area_deep : 2
         */

        private String area_id;
        private String area_name;
        private String area_parent_id;
        private String area_sort;
        private String area_deep;

        @Override
        public String toString() {
            return "PCAData{" +
                    "area_id='" + area_id + '\'' +
                    ", area_name='" + area_name + '\'' +
                    ", area_parent_id='" + area_parent_id + '\'' +
                    ", area_sort='" + area_sort + '\'' +
                    ", area_deep='" + area_deep + '\'' +
                    '}';
        }

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public String getArea_parent_id() {
            return area_parent_id;
        }

        public void setArea_parent_id(String area_parent_id) {
            this.area_parent_id = area_parent_id;
        }

        public String getArea_sort() {
            return area_sort;
        }

        public void setArea_sort(String area_sort) {
            this.area_sort = area_sort;
        }

        public String getArea_deep() {
            return area_deep;
        }

        public void setArea_deep(String area_deep) {
            this.area_deep = area_deep;
        }

        @Override
        public String getPickerViewText() {
            return area_name;
        }
    }
}
