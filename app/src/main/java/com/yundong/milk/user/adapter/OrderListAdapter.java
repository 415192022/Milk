package com.yundong.milk.user.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yundong.milk.R;
import com.yundong.milk.user.activity.ActivityComment;
import com.yundong.milk.user.activity.OrderDetailActivity;
import com.yundong.milk.user.model.GoodsListModel;
import com.yundong.milk.util.ToastUtil;

import java.util.ArrayList;

/**
 * Created by LMW on 2016/11/17.
 * 我的收藏
 */
public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.GoodsHolder> implements View.OnClickListener {

    private ArrayList<GoodsListModel> mList;
    private Context mContext;

    public OrderListAdapter(Context context) {
        this.mContext = context;
    }

    public void addData(ArrayList<GoodsListModel> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public GoodsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new GoodsHolder(view);
    }

    @Override
    public void onBindViewHolder(final GoodsHolder holder, final int position) {
        Glide.with(mContext).load(R.mipmap.img_test).into(holder.imgShopPic);
        holder.txtShopName.setText("Milk House");
        holder.txtRefundStatus.setText("退款成功");
        Glide.with(mContext).load(R.mipmap.img_test).into(holder.imgGoodsPic);
        holder.txtGoodsName.setText("【新品上市】百岁山矿泉水348ml*24瓶整箱 x1");
        holder.txtShopPrice.setText("￥169.00");
        holder.txtMarketPrice.setText("￥199.00");
        holder.txtGoodsAttr.setText("全脂250ml * 24");
        holder.txtExpress.setText("包邮");
        holder.txtTotalPrice.setText("￥169.00");
        holder.txtTime.setText("0天11小时59分钟");
        holder.btnCommonRight.setText(R.string.cancel);
        holder.btnCommonCenter.setText(R.string.deleteOrder);
        holder.txtMarketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
        holder.btnCommonRight.setOnClickListener(this);
        holder.btnCommonCenter.setOnClickListener(this);
        holder.itemView.setOnClickListener(this);
        holder.btnCommonRight.setTag(position);
        holder.btnCommonCenter.setTag(position);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCommonRight:
                ToastUtil.showShortToast("  ** Right ");
                mContext.startActivity(new Intent(mContext, ActivityComment.class));
                break;
            case R.id.btnCommonCenter:
                ToastUtil.showShortToast("  ** Center ");
                break;
            default:
                mContext.startActivity(new Intent(mContext, OrderDetailActivity.class));
                break;
        }
    }

    public static class GoodsHolder extends RecyclerView.ViewHolder {

        private ImageView imgShopPic;
        private TextView txtShopName;
        private TextView txtRefundStatus;
        private ImageView imgGoodsPic;
        private TextView txtGoodsName;
        private TextView txtShopPrice;
        private TextView txtMarketPrice;
        private TextView txtGoodsAttr;
        private TextView txtExpress;
        private TextView txtTotalPrice;
        private TextView txtTime;
        private TextView btnCommonRight;
        private TextView btnCommonCenter;

        public GoodsHolder(View itemView) {
            super(itemView);
            imgShopPic = (ImageView) itemView.findViewById(R.id.imgShopPic);
            txtShopName = (TextView) itemView.findViewById(R.id.txtShopName);
            txtRefundStatus = (TextView) itemView.findViewById(R.id.txtRefundStatus);
            imgGoodsPic = (ImageView) itemView.findViewById(R.id.imgGoodsPic);
            txtGoodsName = (TextView) itemView.findViewById(R.id.txtGoodsName);
            txtShopPrice = (TextView) itemView.findViewById(R.id.txtShopPrice);
            txtMarketPrice = (TextView) itemView.findViewById(R.id.txtMarketPrice);
            txtGoodsAttr = (TextView) itemView.findViewById(R.id.txtGoodsAttr);
            txtExpress = (TextView) itemView.findViewById(R.id.txtExpress);
            txtTotalPrice = (TextView) itemView.findViewById(R.id.txtTotalPrice);
            txtTime = (TextView) itemView.findViewById(R.id.txtTime);
            btnCommonRight = (TextView) itemView.findViewById(R.id.btnCommonRight);
            btnCommonCenter = (TextView) itemView.findViewById(R.id.btnCommonCenter);
        }
    }
}