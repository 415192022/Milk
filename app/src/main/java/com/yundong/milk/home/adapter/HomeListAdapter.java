package com.yundong.milk.home.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yundong.milk.R;
import com.yundong.milk.user.model.GoodsListModel;
import com.yundong.milk.util.ToastUtil;

import java.util.ArrayList;

/**
 * Created by lj on 2016/11/17.
 *  我的收藏
 */
public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.GoodsHolder> implements View.OnClickListener{

    private ArrayList<GoodsListModel> mList;
    private Context mContext;

    public HomeListAdapter(Context context) {
        this.mContext = context;
    }

    public void addData(ArrayList<GoodsListModel> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public GoodsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_goods_list, parent, false);
        return new GoodsHolder(view);
    }

    @Override
    public void onBindViewHolder(final GoodsHolder holder, final int position) {
        Glide.with(mContext).load(R.mipmap.img_test).into(holder.imgPic);
        holder.txtGoodsAttr.setText("【新品上市】百岁山矿泉水348ml*24瓶 整箱百岁山水中贵族, 好水等你来购~");
        holder.txtShopPrice.setText("￥99.00");
        holder.txtMarketPrice.setText("￥199.00");
        holder.txtMarketPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
        holder.btnBuyHome.setOnClickListener(this);
        holder.btnBuyHome.setTag(position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public void onClick(View view) {
        int index = (Integer) view.getTag();
        switch (view.getId()){
            case R.id.btnBuyHome:
                ToastUtil.showShortToast("  **  " + index);
                break;
        }
    }

    public static class GoodsHolder extends RecyclerView.ViewHolder {

        private ImageView imgPic;
        private TextView txtGoodsAttr;
        private TextView txtShopPrice;
        private TextView txtMarketPrice;
        private Button btnBuyHome;

        public GoodsHolder(View itemView) {
            super(itemView);

            imgPic = (ImageView) itemView.findViewById(R.id.imgPic);
            txtGoodsAttr = (TextView) itemView.findViewById(R.id.txtGoodsAttr);
            txtShopPrice = (TextView) itemView.findViewById(R.id.txtShopPrice);
            txtMarketPrice = (TextView) itemView.findViewById(R.id.txtMarketPrice);
            btnBuyHome = (Button) itemView.findViewById(R.id.btnBuyHome);
        }
    }
}