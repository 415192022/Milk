package com.yundong.milk.model;

/**
 * Created by MingweiLi on 2017/3/1.
 */

public class TypeBrandBean extends BaseReceivesBean<TypeBrandBean> {
    private String brand_id;
    private String class_id;
    private String brand_name;
    private String sort;
    private String add_time;
    private String update_time;

    @Override
    public String toString() {
        return "TypeBrandBean{" +
                "brand_id='" + brand_id + '\'' +
                ", class_id='" + class_id + '\'' +
                ", brand_name='" + brand_name + '\'' +
                ", sort='" + sort + '\'' +
                ", add_time='" + add_time + '\'' +
                ", update_time='" + update_time + '\'' +
                '}';
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public String getSort() {
        return sort;
    }

    public String getAdd_time() {
        return add_time;
    }

    public String getUpdate_time() {
        return update_time;
    }
}
