package com.yundong.milk.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yundong.milk.R;
import com.yundong.milk.home.activity.GoodsListTwoSortActivity;
import com.yundong.milk.model.RecommentTypeBean;
import com.yundong.milk.util.rxbus.RxBus;
import com.yundong.milk.widget.CircleImageView;

import java.util.ArrayList;

/**
 * Created by MingweiLi on 2017/3/14.
 */

public class RecommentTypeAdapter extends RecyclerView.Adapter<RecommentTypeAdapter.RecommentViewHolder> {
    private Context context;
    private ArrayList<RecommentTypeBean> recommentTypeBeanArrayList = new ArrayList<>();

    public RecommentTypeAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<RecommentTypeBean> getRecommentTypeBeanArrayList() {
        return recommentTypeBeanArrayList;
    }

    @Override
    public RecommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_recomment_type, null);
        return new RecommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecommentViewHolder holder, final int position) {
        Glide.with(context)
                .load(recommentTypeBeanArrayList.get(position).getGc_icon())
                .into(holder.civ_comment_image);
        holder.tv_comment_text.setText(recommentTypeBeanArrayList.get(position).getGc_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.getDefault().post(recommentTypeBeanArrayList.get(position).setPosition(position));
                context.startActivity(new Intent(context, GoodsListTwoSortActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return recommentTypeBeanArrayList.size();
    }

    public class RecommentViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView civ_comment_image;
        public TextView tv_comment_text;

        public RecommentViewHolder(View itemView) {
            super(itemView);
            civ_comment_image = (CircleImageView) itemView.findViewById(R.id.civ_comment_image);
            tv_comment_text = (TextView) itemView.findViewById(R.id.tv_comment_text);
        }
    }
}
