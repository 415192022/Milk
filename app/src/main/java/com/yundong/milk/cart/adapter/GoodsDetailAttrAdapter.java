package com.yundong.milk.cart.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yundong.milk.R;

/**
 * Created by lj on 2017/1/9.
 * 商品详情页的商品属性
 */
public class GoodsDetailAttrAdapter extends BaseAdapter{

    private Context mContext;
    private int mClickPosition = -1;

    public GoodsDetailAttrAdapter(Context context){
        this.mContext = context;
    }

    public void setSelection(int position){
        mClickPosition = position;
    }

    @Override
    public int getCount() {
        return 5;
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
            view = View.inflate(mContext, R.layout.item_goods_attr, null);
            viewHolder.txtContent = (TextView) view.findViewById(R.id.txtContent);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.txtContent.setText("全脂250ml * " + position + "0");
        if (mClickPosition == position) {
            viewHolder.txtContent.setBackgroundResource(R.mipmap.img_select_goods_attr_true);
            viewHolder.txtContent.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        } else {
            viewHolder.txtContent.setBackgroundResource(R.mipmap.img_select_goods_attr_false);
            viewHolder.txtContent.setTextColor(ContextCompat.getColor(mContext, R.color.titleColor));
        }
        return view;
    }

    public class ViewHolder{
        private TextView txtContent;
    }
}
