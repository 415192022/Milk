package com.yundong.milk.user.model;

import java.util.ArrayList;

/**
 * Created by lj on 2016/12/5.
 * 获取省份
 */
public class SelectProvinceModel {

    public String type;
    public ArrayList<Data> data;

    public class Data{
        public String area_parent_id;
        public String area_id;
        public String area_sort;
        public String area_deep;
        public String area_name;
    }


}
