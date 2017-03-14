package com.yundong.milk.home.adapter;

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
import com.yundong.milk.model.TypeGoodsBean;
import com.yundong.milk.user.model.GoodsListModel;
import com.yundong.milk.util.ToastUtil;

import java.util.ArrayList;

/**
 * Created by lj on 2016/11/17.
 * 我的收藏
 */
public class GoodsSortListTwoAdapter extends RecyclerView.Adapter<GoodsSortListTwoAdapter.GoodsHolder>  {

    private ArrayList<TypeGoodsBean.TypeGoodsBeanDataO.TypeGoodsBeanDataA> mList = new ArrayList<TypeGoodsBean.TypeGoodsBeanDataO.TypeGoodsBeanDataA>();
    private Activity mContext;

    public GoodsSortListTwoAdapter(Activity context, ArrayList<TypeGoodsBean.TypeGoodsBeanDataO.TypeGoodsBeanDataA> mList) {
        this.mList = mList;
        this.mContext = context;
    }

    public GoodsSortListTwoAdapter(Activity context) {
        this.mContext = context;
    }

    public void addAllData(ArrayList<TypeGoodsBean.TypeGoodsBeanDataO.TypeGoodsBeanDataA> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void addData(TypeGoodsBean.TypeGoodsBeanDataO.TypeGoodsBeanDataA typeGoodsBeanDataA) {
        mList.add(typeGoodsBeanDataA);
        notifyDataSetChanged();
    }

    public ArrayList<TypeGoodsBean.TypeGoodsBeanDataO.TypeGoodsBeanDataA> getData() {
        return mList;
    }

    @Override
    public GoodsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods_list_two, parent, false);
        return new GoodsHolder(view);
    }

    @Override
    public void onBindViewHolder(final GoodsHolder holder, final int position) {
        Glide.with(mContext).load(R.mipmap.img_test).into(holder.imgGoodsPic);
        holder.txtGoodsContent.setText(mList.get(position).getGoods_name());
        holder.txtPrice.setText("￥" + mList.get(position).getGoods_price());
        holder.txtSellNum.setText(mList.get(position).getGoods_marketprice());
        holder.itemView.setTag(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                intent.putExtra("GOODS_ID", mList.get(position).getGoods_id());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class GoodsHolder extends RecyclerView.ViewHolder {

        private ImageView imgGoodsPic;
        private TextView txtGoodsContent;
        private TextView txtPrice;
        private TextView txtSellNum;

        public GoodsHolder(View itemView) {
            super(itemView);
            imgGoodsPic = (ImageView) itemView.findViewById(R.id.imgGoodsPic);
            txtGoodsContent = (TextView) itemView.findViewById(R.id.txtGoodsContent);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            txtSellNum = (TextView) itemView.findViewById(R.id.txtSellNum);
        }
    }
}