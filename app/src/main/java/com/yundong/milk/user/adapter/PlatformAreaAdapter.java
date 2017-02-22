package com.yundong.milk.user.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yundong.milk.R;

import java.util.ArrayList;

/**
 * Created by lj on 2017/1/4.
 * 退款售后
 */
public class PlatformAreaAdapter extends BaseAdapter{

    private ArrayList<String> mList;
    private Context mContext;
    private int clickTemp = -1;

    public PlatformAreaAdapter(Context context){
//        this.mList = list;
        this.mContext = context;
    }


    public void setSelection(int position) {
        clickTemp = position;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (viewHolder == null){
            viewHolder = new ViewHolder();
            view = View.inflate(mContext, R.layout.item_refund_after_sale, null);
            viewHolder.txtRefundReason = (TextView) view.findViewById(R.id.txtRefundReason);
            viewHolder.imgCheck = (ImageView) view.findViewById(R.id.imgCheck);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.txtRefundReason.setText("商品质量有问题 " + position);
        if (clickTemp == position) {
            viewHolder.imgCheck.setImageResource(R.mipmap.img_check_true);
        } else {
            viewHolder.imgCheck.setImageResource(R.mipmap.img_check_false);
        }
        return view;
    }

    public class ViewHolder{

        private TextView txtRefundReason;
        private ImageView imgCheck;
    }
}
