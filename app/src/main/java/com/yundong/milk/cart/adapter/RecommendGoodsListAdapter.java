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
import com.yundong.milk.model.GoodsClassCommentBean;

import java.util.ArrayList;

/**
 * Created by lj on 2016/11/17.
 * 我的收藏
 */
public class RecommendGoodsListAdapter extends RecyclerView.Adapter<RecommendGoodsListAdapter.GoodsHolder> {

    private ArrayList<GoodsClassCommentBean.GoodsClassCommentDataO.GoodsClassCommentDataA> mList = new ArrayList<>();
    private Activity mContext;

    public RecommendGoodsListAdapter(Activity context) {
        this.mContext = context;
    }

    public ArrayList<GoodsClassCommentBean.GoodsClassCommentDataO.GoodsClassCommentDataA> getmList() {
        return mList;
    }

    public void addData(ArrayList<GoodsClassCommentBean.GoodsClassCommentDataO.GoodsClassCommentDataA> list) {
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
        if (mList != null && mList.size() > 0) {
            Glide.with(mContext).load(mList.get(position).getGoods_main_image()).into(holder.imgCollectionPic);
            holder.txtCollectionContent.setText(mList.get(position).getGoods_name());
            holder.txtPrice.setText("¥" + mList.get(position).getGoods_price());
            holder.txtCollectionNum.setText("月售" + mList.get(position).getGoods_salenum());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(mContext, GoodsDetailActivity.class);
                    intent.putExtra("GOODS_ID",mList.get(position).getGoods_id());
                    mContext.startActivity(intent);
                }
            });
            holder.itemView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
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