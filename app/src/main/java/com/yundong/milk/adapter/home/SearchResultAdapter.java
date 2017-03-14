package com.yundong.milk.adapter.home;

import android.content.Context;
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
import com.yundong.milk.home.activity.GoodsListTwoSortActivity;
import com.yundong.milk.imagechoose.bean.Image;
import com.yundong.milk.model.RecommentTypeBean;
import com.yundong.milk.model.SearchResultBean;
import com.yundong.milk.util.rxbus.RxBus;
import com.yundong.milk.widget.CircleImageView;

import java.util.ArrayList;

/**
 * Created by MingweiLi on 2017/3/14.
 */

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchResultAdapterViewHolder> {
    private Context context;
    private ArrayList<SearchResultBean.SearchResultData.SearchResultArray> searchResultArrays = new ArrayList<>();

    public SearchResultAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<SearchResultBean.SearchResultData.SearchResultArray> getSearchResultArrays() {
        return searchResultArrays;
    }

    @Override
    public SearchResultAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_search_result, null);
        return new SearchResultAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchResultAdapterViewHolder holder, final int position) {
        Glide.with(context)
                .load(searchResultArrays.get(position).getGoods_main_image())
                .into(holder.iv_item_search_image);
        holder.tv_item_search_name.setText(searchResultArrays.get(position).getGoods_name());
        holder.tv_item_search_price.setText(searchResultArrays.get(position).getGoods_price());
        holder.tv_item_search_number.setText(searchResultArrays.get(position).getGoods_salenum());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, GoodsDetailActivity.class);
                intent.putExtra("GOODS_ID",searchResultArrays.get(position).getGoods_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchResultArrays.size();
    }

    public class SearchResultAdapterViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_item_search_image;
        public TextView tv_item_search_name;
        public TextView tv_item_search_price;
        public TextView tv_item_search_number;

        public SearchResultAdapterViewHolder(View itemView) {
            super(itemView);
            iv_item_search_image = (ImageView) itemView.findViewById(R.id.iv_item_search_image);
            tv_item_search_name = (TextView) itemView.findViewById(R.id.tv_item_search_name);
            tv_item_search_price = (TextView) itemView.findViewById(R.id.tv_item_search_price);
            tv_item_search_number = (TextView) itemView.findViewById(R.id.tv_item_search_number);
        }
    }
}
