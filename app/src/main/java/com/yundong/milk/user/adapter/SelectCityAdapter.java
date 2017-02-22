package com.yundong.milk.user.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yundong.milk.R;
import com.yundong.milk.user.model.SelectCityModel;

import java.util.ArrayList;

/**
 * Created by lj on 2016/12/5.
 * 城市选择
 */
public class SelectCityAdapter extends BaseAdapter{

    private ArrayList<SelectCityModel> mList;
    private Context mContext;

    public SelectCityAdapter(ArrayList<SelectCityModel> list, Context context){
        this.mList = list;
        this.mContext = context;
    }

    public String getCityName(int index){
        return mList.get(index).area_name;
    }

    public String getCityId(int index){
        return mList.get(index).area_id;
    }

    @Override
    public int getCount() {
        return mList.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            view = View.inflate(mContext, R.layout.item_select_city, null);
            viewHolder.txtCity = (TextView) view.findViewById(R.id.txtCity);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.txtCity.setText(mList.get(i).area_name);
        return view;
    }

    public class ViewHolder{
        private TextView txtCity;
    }
}
