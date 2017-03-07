package com.yundong.milk.adapter.order;

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
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.OrderListBean;
import com.yundong.milk.present.MineOrderFragmentPresenter;
import com.yundong.milk.user.activity.ActivityComment;
import com.yundong.milk.user.adapter.OrderListAdapter;
import com.yundong.milk.user.fragment.MineOrderFragment;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.widget.dialog.SweetAlertDialog;

import java.util.ArrayList;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public class WatingReceiveGoodsAdapter extends RecyclerView.Adapter<WatingReceiveGoodsAdapter.WatingReceiveGoodsHolder> {
    private ArrayList<OrderListBean.OrderListData.OrderListDataArray> mList = new ArrayList<>();
    private Context mContext;
    private MineOrderFragment mineOrderFragment;

    public WatingReceiveGoodsAdapter(Context context) {
        this.mContext = context;
    }

    public WatingReceiveGoodsAdapter(Context context, MineOrderFragment mineOrderFragment) {
        this.mineOrderFragment = mineOrderFragment;
        this.mContext = context;
    }

    public ArrayList<OrderListBean.OrderListData.OrderListDataArray> getmList() {
        return mList;
    }

    public void addData(ArrayList<OrderListBean.OrderListData.OrderListDataArray> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public WatingReceiveGoodsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new WatingReceiveGoodsHolder(view);
    }

    @Override
    public void onBindViewHolder(final WatingReceiveGoodsHolder holder, final int position) {
        if (mList.size() > 0 && null != mList) {
            Glide.with(mContext).load(mList.get(position).getGoods_main_image()).into(holder.imgShopPic);
            holder.txtShopName.setText(mList.get(position).getGoods_name());
            String orderState = "";
            if (mList.get(position).getOrder_state().equals("0")) {
                orderState = "已取消";
            } else if (mList.get(position).getOrder_state().equals("1")) {
                orderState = "未付款";
            } else if (mList.get(position).getOrder_state().equals("2")) {
                orderState = "已付款";
            } else if (mList.get(position).getOrder_state().equals("3")) {
                orderState = "已发货";
            } else if (mList.get(position).getOrder_state().equals("4")) {
                orderState = "已收货";
            }
            String refundStatus = "";
            if (mList.get(position).getService_state().equals("0")) {
                refundStatus = "正常 ";
            } else if (mList.get(position).getService_state().equals("1")) {
                refundStatus = "退货";
            }
            holder.txtRefundStatus.setText(refundStatus);


            Glide.with(mContext).load(mList.get(position).getGoods_main_image()).into(holder.imgGoodsPic);
            holder.txtGoodsName.setText(mList.get(position).getGoods_name());
            holder.txtShopPrice.setText("￥" + mList.get(position).getGoods_price());
            holder.txtMarketPrice.setText("￥" + mList.get(position).getGoods_marketprice());
            holder.txtGoodsAttr.setText("全脂250ml * 24");
            holder.txtExpress.setText("包邮");
            holder.txtTotalPrice.setText("￥" + mList.get(position).getOrder_amount());
            holder.txtTime.setText("0天11小时59分钟");
            holder.btnCommonRight.setText(R.string.cancel);
            String isComment = "";
            if (mList.get(position).getIs_comment().equals("0")) {
                isComment = "评价";
                holder.tv_comment.setEnabled(true);
            } else if (mList.get(position).getIs_comment().equals("1")) {
                isComment = "已评价";
                holder.tv_comment.setEnabled(false);
            }
            holder.tv_comment.setText(isComment);

            holder.btnCommonCenter.setText(R.string.deleteOrder);
            holder.txtMarketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
            holder.btnCommonRight.setTag(position);
            holder.btnCommonCenter.setTag(position);
            holder.itemView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class WatingReceiveGoodsHolder extends RecyclerView.ViewHolder {

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
        private TextView tv_comment;

        public WatingReceiveGoodsHolder(View itemView) {
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
            tv_comment = (TextView) itemView.findViewById(R.id.tv_comment);
        }
    }
}
