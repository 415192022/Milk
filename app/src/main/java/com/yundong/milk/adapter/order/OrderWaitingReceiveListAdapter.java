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
import com.yundong.milk.cart.activity.ConfirmOrderActivity;
import com.yundong.milk.cart.activity.GoodsDetailActivity;
import com.yundong.milk.manager.YunDongApplication;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.OrderListBean;
import com.yundong.milk.present.MineOrderFragmentPresenter;
import com.yundong.milk.user.fragment.MineOrderFragment;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.util.rxbus.RxBus;
import com.yundong.milk.view.ICancleOrderView;
import com.yundong.milk.view.IConfirmReceiveGoodsView;
import com.yundong.milk.widget.dialog.SweetAlertDialog;

import java.util.ArrayList;

/**
 * Created by LMW on 2016/11/17.
 * 我的收藏
 */
public class OrderWaitingReceiveListAdapter extends RecyclerView.Adapter<OrderWaitingReceiveListAdapter.GoodsHolder>
        implements IConfirmReceiveGoodsView {

    private ArrayList<OrderListBean.OrderListData.OrderListDataArray> mList = new ArrayList<>();
    private Context mContext;
    private MineOrderFragment mineOrderFragment;
    private MineOrderFragmentPresenter mineOrderFragmentPresenter;

    public OrderWaitingReceiveListAdapter(Context context) {
        this.mContext = context;
        mineOrderFragmentPresenter = MineOrderFragmentPresenter.getInstance().with(this);
    }

    public OrderWaitingReceiveListAdapter(Context context, MineOrderFragment mineOrderFragment) {
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
    public OrderWaitingReceiveListAdapter.GoodsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderWaitingReceiveListAdapter.GoodsHolder(view);
    }

    @Override
    public void onBindViewHolder(final OrderWaitingReceiveListAdapter.GoodsHolder holder, final int position) {
        if (mList.size() > 0 && null != mList) {
            Glide.with(mContext).load(mList.get(position).getGoods_main_image()).into(holder.imgShopPic);
            holder.txtShopName.setText(mList.get(position).getGoods_name());
            String orderState = "";
            if (mList.get(position).getOrder_state().equals("0")) {
                orderState = "已取消";
            } else if (mList.get(position).getOrder_state().equals("1")) {
                orderState = "未付款";
            } else if (mList.get(position).getOrder_state().equals("2")) {
//                orderState = "已付款";
                orderState = "待发货";
                holder.btnCommonRight.setVisibility(View.INVISIBLE);
                holder.btnCommonRight.setVisibility(View.VISIBLE);
                holder.btnCommonRight.setText("确认收货");
            } else if (mList.get(position).getOrder_state().equals("3")) {
//                orderState = "已发货";
                orderState = "待收货";
                holder.btnCommonRight.setVisibility(View.VISIBLE);
                holder.btnCommonRight.setText("确认收货");
            } else if (mList.get(position).getOrder_state().equals("4")) {
                orderState = "已收货";
                holder.btnCommonRight.setVisibility(View.INVISIBLE);
            }

            holder.tv_comment.setVisibility(View.INVISIBLE);
            holder.btnCommonCenter.setVisibility(View.INVISIBLE);
            holder.txtRefundStatus.setText(orderState);
            holder.btnCommonCenter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //付款.
                    RxBus.getDefault().post(mList.get(position));
                    Intent intent = new Intent(mContext, ConfirmOrderActivity.class);
                    mContext.startActivity(intent);
                }
            });


            Glide.with(mContext).load(mList.get(position).getGoods_main_image()).into(holder.imgGoodsPic);
            holder.txtGoodsName.setText(mList.get(position).getGoods_name());
            holder.txtShopPrice.setText("￥" + mList.get(position).getGoods_price());
            holder.txtMarketPrice.setText("￥" + mList.get(position).getGoods_marketprice());
//            holder.txtGoodsAttr.setText("全脂250ml * 24");
            holder.txtExpress.setText("包邮");
            holder.txtTotalPrice.setText("￥" + mList.get(position).getOrder_amount());
//            holder.txtTime.setText("0天11小时59分钟");
            holder.btnCommonRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final SweetAlertDialog dialog = new SweetAlertDialog(mContext);
                    dialog.showCancelButton(true);
                    dialog.setTitleText("确认收货？");
                    dialog.setCancelText(mContext.getString(R.string.cancel));
                    dialog.setConfirmText(mContext.getString(R.string.confirm));
                    dialog.show();
                    dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            dialog.dismiss();
                            //确认收货
                            mineOrderFragmentPresenter.confirmReceiveGoods(mList.get(position).getOrder_id());

                        }
                    });
                }
            });


            holder.txtMarketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
            holder.btnCommonRight.setTag(position);
            holder.btnCommonCenter.setTag(position);
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
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void confirmReceiveGoods(BaseReceiveBean baseReceiveBean) {

    }

    @Override
    public void confirmReceiveGoodsOnError(String e) {
        mineOrderFragment
                .maineOrderActivityPresenter
                .orderList(YunDongApplication.getLoginBean().getData().getUserinfo().getId(), "3", "", "1", "20");
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
        private TextView tv_comment;

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
            tv_comment = (TextView) itemView.findViewById(R.id.tv_comment);
        }
    }
}