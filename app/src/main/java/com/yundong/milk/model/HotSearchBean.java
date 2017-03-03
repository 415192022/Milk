package com.yundong.milk.model;

/**
 * Created by MingweiLi on 2017/2/28.
 */

public class HotSearchBean extends BaseReceivesBean<HotSearchBean>{
    private String id;
    private String name;
    private String url;
    private String sort;
    private String status;
    private String add_time;
    private String update_time;

    @Override
    public String toString() {
        return "HotSearchBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", sort='" + sort + '\'' +
                ", status='" + status + '\'' +
                ", add_time='" + add_time + '\'' +
                ", update_time='" + update_time + '\'' +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getSort() {
        return sort;
    }

    public String getStatus() {
        return status;
    }

    public String getAdd_time() {
        return add_time;
    }

    public String getUpdate_time() {
        return update_time;
    }
}
