package com.yundong.milk.model;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public class AllTypeBean extends BaseReceivesBean<AllTypeBean> {
    private String gc_id;
    private String gc_name;
    private String gc_icon;
    private String gc_parent_id;
    private String gc_sort;
    private String recommend;

    @Override
    public String toString() {
        return "RecommentTypeBean{" +
                "gc_id='" + gc_id + '\'' +
                ", gc_name='" + gc_name + '\'' +
                ", gc_icon='" + gc_icon + '\'' +
                ", gc_parent_id='" + gc_parent_id + '\'' +
                ", gc_sort='" + gc_sort + '\'' +
                ", recommend='" + recommend + '\'' +
                '}';
    }

    public void setGc_id(String gc_id) {
        this.gc_id = gc_id;
    }

    public void setGc_name(String gc_name) {
        this.gc_name = gc_name;
    }

    public void setGc_icon(String gc_icon) {
        this.gc_icon = gc_icon;
    }

    public void setGc_parent_id(String gc_parent_id) {
        this.gc_parent_id = gc_parent_id;
    }

    public void setGc_sort(String gc_sort) {
        this.gc_sort = gc_sort;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getGc_id() {

        return gc_id;
    }

    public String getGc_name() {
        return gc_name;
    }

    public String getGc_icon() {
        return gc_icon;
    }

    public String getGc_parent_id() {
        return gc_parent_id;
    }

    public String getGc_sort() {
        return gc_sort;
    }

    public String getRecommend() {
        return recommend;
    }
}
