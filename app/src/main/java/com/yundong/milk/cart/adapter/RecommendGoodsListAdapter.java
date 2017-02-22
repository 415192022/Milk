package com.yundong.milk.cart.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yundong.milk.R;
import com.yundong.milk.cart.activity.GoodsDetailActivity;
import com.yundong.milk.user.model.GoodsListModel;

import java.util.ArrayList;

/**
 * Created by lj on 2016/11/17.
 *  我的收藏
 */
public class RecommendGoodsListAdapter extends RecyclerView.Adapter<RecommendGoodsListAdapter.GoodsHolder> implements View.OnClickListener{

    private ArrayList<GoodsListModel> mList;
    private Activity mContext;

    public RecommendGoodsListAdapter(Activity context) {
        this.mContext = context;
    }

    public void addData(ArrayList<GoodsListModel> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public GoodsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine_collection, parent, false);
        return new GoodsHolder(view);
    }

    @Override
    public void onBindViewHolder(final GoodsHolder holder, final int position) {
        Glide.with(mContext).load(R.mipmap.img_test).into(holder.imgCollectionPic);
        holder.txtCollectionContent.setText("房管局色粉hi粉红色的解放后几号付款的叫声");
        holder.txtPrice.setText("¥199.00");
        holder.txtCollectionNum.setText("月售2831");
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            default:
                mContext.startActivity(new Intent(mContext, GoodsDetailActivity.class));
                break;
        }
    }

    public static class GoodsHolder extends RecyclerView.ViewHolder {

        private ImageView imgCollectionPic;
        private TextView txtCollectionContent;
        private TextView txtPrice;
        private TextView txtCollectionNum;

        public GoodsHolder(View itemView) {
            super(itemView);
            imgCollectionPic = (ImageView) itemView.findViewById(R.id.imgCollectionPic);
            txtCollectionContent = (TextView) itemView.findViewById(R.id.txtCollectionContent);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            txtCollectionNum = (TextView) itemView.findViewById(R.id.txtCollectionNum);
        }
    }
}