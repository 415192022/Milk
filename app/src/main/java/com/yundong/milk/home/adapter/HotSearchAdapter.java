package com.yundong.milk.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yundong.milk.R;

/**
 * Created by lj on 2017/1/5.
 * 热门搜索
 */
public class HotSearchAdapter extends BaseAdapter{

    private Context mContext;

    public HotSearchAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 9;
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
            view = View.inflate(mContext, R.layout.item_hot_search, null);
            viewHolder.txtTitle = (TextView) view.findViewById(R.id.txtTitle);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.txtTitle.setText("老年牛奶");
        return view;
    }

    public class ViewHolder{
        private TextView txtTitle;
    }
}
