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
import com.yundong.milk.model.GoodsCommentBean;
import com.yundong.milk.user.model.GoodsListModel;
import com.yundong.milk.util.RxBusUtil;
import com.yundong.milk.util.ToastUtil;

import java.util.ArrayList;

/**
 * Created by lj on 2016/11/17.
 *  我的收藏
 */
public class HomeGoodsListAdapter extends BaseAdapter{

    private ArrayList<GoodsCommentBean.GoodsCommentDataO.GoodsCommentDataA> mList=new ArrayList<>();
    private Context mContext;

    public ArrayList<GoodsCommentBean.GoodsCommentDataO.GoodsCommentDataA> getmList() {
        return mList;
    }

    public HomeGoodsListAdapter(Context context) {
        this.mContext = context;
    }

    public void addAllData(ArrayList<GoodsCommentBean.GoodsCommentDataO.GoodsCommentDataA> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }
    public void addData(GoodsCommentBean.GoodsCommentDataO.GoodsCommentDataA goodsCommentDataA) {
        mList.add(goodsCommentDataA);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
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
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (viewHolder == null){
            viewHolder = new ViewHolder();
            view = View.inflate(mContext, R.layout.item_home_goods_list, null);
            viewHolder.imgPic = (ImageView) view.findViewById(R.id.imgPic);
            viewHolder.txtGoodsAttr = (TextView) view.findViewById(R.id.txtGoodsAttr);
            viewHolder.txtShopPrice = (TextView) view.findViewById(R.id.txtShopPrice);
            viewHolder.txtMarketPrice = (TextView) view.findViewById(R.id.txtMarketPrice);
            viewHolder.btnBuyHome = (Button) view.findViewById(R.id.btnBuyHome);
            view.setTag(position);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if(mList.size()>0){
            Glide.with(mContext).load(mList.get(position).getGoods_main_image()).into(viewHolder.imgPic);
            viewHolder.txtGoodsAttr.setText(mList.get(position).getGoods_name());
            viewHolder.txtShopPrice.setText("￥"+mList.get(position).getGoods_price());
            viewHolder.txtMarketPrice.setText("￥"+mList.get(position).getGoods_marketprice());
            viewHolder.txtMarketPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
            viewHolder.btnBuyHome.setTag(position);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, GoodsDetailActivity.class);
                intent.putExtra("GOODS_ID",mList.get(position).getGoods_id());
                mContext.startActivity(intent);
            }
        });
        viewHolder.btnBuyHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, GoodsDetailActivity.class);
                intent.putExtra("GOODS_ID",mList.get(position).getGoods_id());
                mContext.startActivity(intent);
            }
        });
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