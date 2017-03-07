package com.yundong.milk.model.send;

import java.util.ArrayList;

/**
 * Created by MingweiLi on 2017/3/7.
 */

public class CommentImageBean {
    private ArrayList<String> images;

    @Override
    public String toString() {
        return "CommentImageBean{" +
                "images=" + images +
                '}';
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public ArrayList<String> getImages() {
        return images;
    }
}
