package com.yundong.milk.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yundong.milk.R;
import com.yundong.milk.cart.activity.GoodsDetailActivity;
import com.yundong.milk.user.model.GoodsListModel;

import java.util.ArrayList;

/**
 * Created by lj on 2016/11/17.
 *  我的收藏
 */
public class HomeGoodsListAdapter extends BaseAdapter implements View.OnClickListener{

    private ArrayList<GoodsListModel> mList;
    private Context mContext;

    public HomeGoodsListAdapter(Context context) {
        this.mContext = context;
    }

    public void addData(ArrayList<GoodsListModel> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            default:
                mContext.startActivity(new Intent(mContext, GoodsDetailActivity.class));
                break;
        }
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (viewHolder == null){
            viewHolder = new ViewHolder();
            view = View.inflate(mContext, R.layout.item_home_goods_list, null);
            viewHolder.imgPic = (ImageView) view.findViewById(R.id.imgPic);
            viewHolder.txtGoodsAttr = (TextView) view.findViewById(R.id.txtGoodsAttr);
            viewHolder.txtShopPrice = (TextView) view.findViewById(R.id.txtShopPrice);
            viewHolder.txtMarketPrice = (TextView) view.findViewById(R.id.txtMarketPrice);
            viewHolder.btnBuyHome = (Button) view.findViewById(R.id.btnBuyHome);
            view.setOnClickListener(this);
            view.setTag(position);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(mContext).load(R.mipmap.img_test).into(viewHolder.imgPic);
        viewHolder.txtGoodsAttr.setText("【新品上市】百岁山矿泉水348ml*24瓶 整箱百岁山水中贵族, 好水等你来购~");
        viewHolder.txtShopPrice.setText("￥99.00");
        viewHolder.txtMarketPrice.setText("￥199.00");
        viewHolder.txtMarketPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
        viewHolder.btnBuyHome.setOnClickListener(this);
        viewHolder.btnBuyHome.setTag(position);
        return view;
    }

    public class ViewHolder{
        private ImageView imgPic;
        private TextView txtGoodsAttr;
        private TextView txtShopPrice;
        private TextView txtMarketPrice;
        private Button btnBuyHome;
    }
}