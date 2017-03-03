package com.yundong.milk.user.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yundong.milk.R;
import com.yundong.milk.user.activity.ApplyForMoney;
import com.yundong.milk.user.activity.MoneyToWhereActivity;
import com.yundong.milk.user.activity.RefundDetailActivity;
import com.yundong.milk.user.model.GoodsListModel;
import com.yundong.milk.widget.dialog.SweetAlertDialog;

import java.util.ArrayList;

/**
 * Created by lj on 2016/11/17.
 *  我的收藏
 */
public class RefundListAdapter extends RecyclerView.Adapter<RefundListAdapter.GoodsHolder> implements View.OnClickListener{

    private ArrayList<GoodsListModel> mList;
    private Context mContext;

    public RefundListAdapter(Context context) {
        this.mContext = context;
    }

    public void addData(ArrayList<GoodsListModel> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public GoodsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_refund, parent, false);
        return new GoodsHolder(view);
    }

    @Override
    public void onBindViewHolder(final GoodsHolder holder, final int position) {
        Glide.with(mContext).load(R.mipmap.img_test).into(holder.imgShopPic);
        holder.txtShopName.setText("Milk House");
        Glide.with(mContext).load(R.mipmap.img_test).into(holder.imgGoodsPic);
        holder.txtGoodsName.setText("【新品上市】百岁山矿泉水348ml*24瓶整箱 x1");
        holder.txtShopPrice.setText("￥169.00");
        holder.txtMarketPrice.setText("￥169.00");
        holder.txtGoodsAttr.setText("全脂250ml * 24");
        holder.txtExpress.setText("包邮");
        holder.txtTotalPrice.setText("￥169.00");
        holder.txtTime.setText("0天11小时59分钟");
        if (position % 2 == 0) {
            holder.btnCommon.setText(R.string.cancel);
            holder.txtRefundStatus.setText("处理中");
        }else {
            holder.btnCommon.setText("钱款去向");
            holder.txtRefundStatus.setText("退款成功");
        }
        holder.btnCommon.setOnClickListener(this);
        holder.btnCommon.setTag(position);
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
//        return mList.size();
        return 20;
    }

    @Override
    public void onClick(View view) {
        int index = (Integer) view.getTag();
        switch (view.getId()){
            case R.id.btnCommon:
                if (index % 2 == 0){//取消
                    final SweetAlertDialog dialog = new SweetAlertDialog(mContext);
                    dialog.showCancelButton(true);
                    dialog.setTitleText(mContext.getString(R.string.whether_cancel_refund));
                    dialog.setCancelText(mContext.getString(R.string.cancel));
                    dialog.setConfirmText(mContext.getString(R.string.confirm));
                    dialog.show();
                    dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            dialog.dismiss();
                        }
                    });
                }else {//钱款去向
                    mContext.startActivity(new Intent(mContext, MoneyToWhereActivity.class));
                }
                break;
            default:
                if (index % 2 == 0) {//取消
                    mContext.startActivity(new Intent(mContext, ApplyForMoney.class));
                }else {//钱款去向
                    mContext.startActivity(new Intent(mContext, RefundDetailActivity.class));
                }
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
        private Button btnCommon;

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
            btnCommon = (Button) itemView.findViewById(R.id.btnCommon);
        }
    }
}