package com.yundong.milk.user.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yundong.milk.R;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.util.XPoupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lx on 2017/2/17.
 */

public class AdapterCommentPhoto extends RecyclerView.Adapter<AdapterCommentPhoto.ViewHolder> {
    private Context context;

    private List<Bitmap> images = new ArrayList<>();

    public AdapterCommentPhoto(Context context) {
        this.context = context;
    }


    private int position = -1;

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {

        return position;
    }

    public AdapterCommentPhoto setData(List<Bitmap> images) {
        this.images = images;
        return this;
    }

    public AdapterCommentPhoto addData(Bitmap image) {
        images.add(image);
        notifyDataSetChanged();
        return this;
    }
    public AdapterCommentPhoto addDataList(List<Bitmap> bitmapList) {
        for (Bitmap bitmap:bitmapList){
            images.add(bitmap);
        }
        notifyDataSetChanged();
        return this;
    }

    public List<Bitmap> getImages() {
        return images;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.iv_add_comment_image.setImageBitmap(images.get(position));
        holder.iv_add_comment_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPosition(position);
                XPoupWindow xPoupWindow = new XPoupWindow((Activity) context);
                xPoupWindow.showPopupWindow();
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_add_comment_image;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_add_comment_image = (ImageView) itemView.findViewById(R.id.iv_add_comment_image);
        }
    }
}
