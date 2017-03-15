package com.yundong.milk.adapter.comment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yundong.milk.R;
import com.yundong.milk.user.adapter.AdapterCommentPhoto;
import com.yundong.milk.util.XPoupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MingweiLi on 2017/3/7.
 */

public class AdapterCommentListImages extends RecyclerView.Adapter<AdapterCommentListImages.CommentListImagesHolder> {
    private Context context;

    private List<String> images = new ArrayList<>();

    public AdapterCommentListImages(Context context) {
        this.context = context;
    }


    private int position = -1;

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {

        return position;
    }

    public AdapterCommentListImages setData(List<String> images) {
        this.images = images;
        return this;
    }

    public AdapterCommentListImages addData(String image) {
        images.add(image);
        notifyDataSetChanged();
        return this;
    }

    public AdapterCommentListImages addDataList(List<String> bitmapList) {
        for (String bitmap : bitmapList) {
            images.add(bitmap);
        }
        notifyDataSetChanged();
        return this;
    }

    public List<String> getImages() {
        return images;
    }

    @Override
    public AdapterCommentListImages.CommentListImagesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment_photo, parent, false);
        return new AdapterCommentListImages.CommentListImagesHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterCommentListImages.CommentListImagesHolder holder, final int position) {
        Glide.with(context)
                .load(images.get(position))
                .into(holder.iv_add_comment_image)
        ;
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class CommentListImagesHolder extends RecyclerView.ViewHolder {
        public ImageView iv_add_comment_image;

        public CommentListImagesHolder(View itemView) {
            super(itemView);
            iv_add_comment_image = (ImageView) itemView.findViewById(R.id.iv_add_comment_image);
        }
    }
}
