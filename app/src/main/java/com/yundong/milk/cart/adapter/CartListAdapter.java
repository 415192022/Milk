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
import com.yundong.milk.cart.activity.ConfirmOrderActivity;
import com.yundong.milk.cart.activity.GoodsDetailActivity;
import com.yundong.milk.user.model.GoodsListModel;
import com.yundong.milk.view.dialog.SweetAlertDialog;

import java.util.ArrayList;

/**
 * Created by lj on 2016/11/17.
 *  我的收藏
 */
public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.GoodsHolder> implements View.OnClickListener{

    private ArrayList<GoodsListModel> mList;
    private Activity mContext;

    public CartListAdapter(Activity context) {
        this.mContext = context;
    }

    public void addData(ArrayList<GoodsListModel> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public GoodsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_list, parent, false);
        return new GoodsHolder(view);
    }

    @Override
    public void onBindViewHolder(final GoodsHolder holder, final int position) {
        Glide.with(mContext).load(R.mipmap.img_test).into(holder.imgGoodsPic);
        holder.txtGoodsName.setText("【新品上市】百岁山矿泉水348ml*24瓶整箱 x1");
        holder.txtShopPrice.setText("￥169.00");
        holder.txtMarketPrice.setText("￥169.00");
        holder.txtGoodsAttr.setText("全脂250ml * 24");
        holder.txtDelete.setOnClickListener(this);
        holder.txtSettlement.setOnClickListener(this);
        holder.itemView.setOnClickListener(this);
        holder.txtDelete.setTag(position);
        holder.txtSettlement.setTag(position);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txtDelete:
                final SweetAlertDialog dialog = new SweetAlertDialog(mContext);
                dialog.showCancelButton(true);
                dialog.setTitleText(mContext.getString(R.string.whether_delete));
                dialog.setCancelText(mContext.getString(R.string.cancel));
                dialog.setConfirmText(mContext.getString(R.string.confirm));
                dialog.show();
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.txtSettlement:
                mContext.startActivity(new Intent(mContext, ConfirmOrderActivity.class));
                break;
            default:
                mContext.startActivity(new Intent(mContext, GoodsDetailActivity.class));
                break;
        }
    }

    public static class GoodsHolder extends RecyclerView.ViewHolder {

        private TextView txtDelete;
        private TextView txtSettlement;
        private ImageView imgGoodsPic;
        private TextView txtGoodsName;
        private TextView txtShopPrice;
        private TextView txtMarketPrice;
        private TextView txtGoodsAttr;

        public GoodsHolder(View itemView) {
            super(itemView);
            txtDelete = (TextView) itemView.findViewById(R.id.txtDelete);
            imgGoodsPic = (ImageView) itemView.findViewById(R.id.imgGoodsPic);
            txtGoodsName = (TextView) itemView.findViewById(R.id.txtGoodsName);
            txtShopPrice = (TextView) itemView.findViewById(R.id.txtShopPrice);
            txtMarketPrice = (TextView) itemView.findViewById(R.id.txtMarketPrice);
            txtGoodsAttr = (TextView) itemView.findViewById(R.id.txtGoodsAttr);
            txtSettlement = (TextView) itemView.findViewById(R.id.txtSettlement);
        }
    }
}