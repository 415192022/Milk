package com.yundong.milk.commodity.adapter;

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
import com.yundong.milk.commodity.model.CommodityListModel;
import com.yundong.milk.home.activity.GoodsListTwoSortActivity;

import java.util.ArrayList;

/**
 * Created by lj on 2016/11/17.
 *  我的收藏
 */
public class CommodityListAdapter extends RecyclerView.Adapter<CommodityListAdapter.GoodsHolder> implements View.OnClickListener{

    private ArrayList<CommodityListModel> mList;
    private Context mContext;

    public CommodityListAdapter(Context context) {
        this.mContext = context;
    }

    public void addData(ArrayList<CommodityListModel> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public GoodsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commodity_list, parent, false);
        return new GoodsHolder(view);
    }

    @Override
    public void onBindViewHolder(final GoodsHolder holder, final int position) {
        Glide.with(mContext).load(R.mipmap.img_bg_goods_test).into(holder.imgGoodsSortBg);
        holder.txtGoodsSortContent.setText("成人牛奶");
        if ((position + 1) % 3 == 0){
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(holder.imgGoodsSortBg.getLayoutParams());
            params.setMargins(0, 0, 15, 0);
            holder.imgGoodsSortBg.setLayoutParams(params);
        }
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            default:
                mContext.startActivity(new Intent(mContext, GoodsListTwoSortActivity.class));
                break;
        }
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