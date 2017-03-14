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
import com.yundong.milk.model.CarListBean;
import com.yundong.milk.model.GoodsAndCountBean;
import com.yundong.milk.present.CarFragmentPresenter;
import com.yundong.milk.user.model.GoodsListModel;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.util.rxbus.RxBus;
import com.yundong.milk.widget.dialog.SweetAlertDialog;

import java.util.ArrayList;

/**
 * Created by lj on 2016/11/17.
 * 我的收藏
 */
public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.GoodsHolder> {

    private ArrayList<CarListBean.CarListDataA> mList = new ArrayList<>();
    private Activity mContext;
    private CarFragmentPresenter carFragmentPresenter;

    public ArrayList<CarListBean.CarListDataA> getmList() {
        return mList;
    }

    public CartListAdapter(Activity context, CarFragmentPresenter carFragmentPresenter) {
        this.carFragmentPresenter = carFragmentPresenter;
        this.mContext = context;
    }

    public void addData(ArrayList<CarListBean.CarListDataA> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void deleteDataByIndex(int position) {
        mList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public GoodsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_list, parent, false);
        return new GoodsHolder(view);
    }

    @Override
    public void onBindViewHolder(final GoodsHolder holder, final int position) {
        if (null != mList && mList.size() > 0) {
            Glide.with(mContext).load(mList.get(position).getGoods_main_image()).into(holder.imgGoodsPic);
            holder.txtGoodsName.setText(mList.get(position).getGoods_name());
            holder.txtShopPrice.setText("￥" + mList.get(position).getGoods_price());
            holder.txtMarketPrice.setText("￥" + mList.get(position).getGoods_marketprice());
//            holder.txtGoodsAttr.setText("全脂250ml * 24");
            holder.txtSettlement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RxBus.getDefault().post(mList.get(position));
                    mContext.startActivity(new Intent(mContext, ConfirmOrderActivity.class));
                }
            });
            holder.txtDelete.setTag(position);
            holder.txtSettlement.setTag(position);
            holder.itemView.setTag(position);


            holder.txtDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
                            ToastUtil.showShortToast("确定");
                            carFragmentPresenter.deleteCar(mList.get(position).getId(), position);
                        }
                    });
                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Intent intent = new Intent(mContext, GoodsDetailActivity.class);
//                    intent.putExtra("GOODS_ID", mList.get(position).getGoods_id());
//                    mContext.startActivity(intent);
                    RxBus.getDefault().post(mList.get(position));
                    mContext.startActivity(new Intent(mContext, ConfirmOrderActivity.class));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
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