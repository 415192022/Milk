package com.yundong.milk.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yundong.milk.R;
import com.yundong.milk.home.activity.GoodsListTwoSortActivity;
import com.yundong.milk.model.RecommentTypeBean;
import com.yundong.milk.util.rxbus.RxBus;

import java.util.ArrayList;

/**
 * Created by MingweiLi on 2017/3/13.
 */

public class GoodsClassAdpter extends RecyclerView.Adapter<GoodsClassAdpter.GoodsHolder> {
    private ArrayList<RecommentTypeBean> mList = new ArrayList<>();
    private Context mContext;

    public GoodsClassAdpter(Context context) {
        this.mContext = context;
    }

    public ArrayList<RecommentTypeBean> getmList() {
        return mList;
    }

    public void addData(ArrayList<RecommentTypeBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public GoodsClassAdpter.GoodsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commodity_list, parent, false);
        return new GoodsClassAdpter.GoodsHolder(view);
    }

    @Override
    public void onBindViewHolder(final GoodsClassAdpter.GoodsHolder holder, final int position) {
        Glide.with(mContext).load(mList.get(position).getGc_icon()).into(holder.imgGoodsSortBg);
        holder.txtGoodsSortContent.setText(mList.get(position).getGc_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.getDefault().post(mList.get(position).setPosition(position));
                mContext.startActivity(new Intent(mContext, GoodsListTwoSortActivity.class));
            }
        });
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class GoodsHolder extends RecyclerView.ViewHolder {

        private ImageView imgGoodsSortBg;
        private TextView txtGoodsSortContent;

        public GoodsHolder(View itemView) {
            super(itemView);
            imgGoodsSortBg = (ImageView) itemView.findViewById(R.id.imgGoodsSortBg);
            txtGoodsSortContent = (TextView) itemView.findViewById(R.id.txtGoodsSortContent);
        }
    }
}
