package com.yundong.milk.home.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yundong.milk.R;
import com.yundong.milk.model.TypeBrandBean;
import com.yundong.milk.util.ToastUtil;

import java.util.ArrayList;

public class GoodsSecondSortAdapter extends BaseAdapter{
	private ArrayList<TypeBrandBean> mListTitles;
	private Context mContext;
	private LayoutInflater mInflater;
	private int selectIndex = -1;

	public ArrayList<TypeBrandBean> getmListTitles() {
		return mListTitles;
	}

	public GoodsSecondSortAdapter(Context context, ArrayList<TypeBrandBean> titles){
		this.mContext = context;
		this.mListTitles = titles;
		mInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		return mListTitles.size();
	}
	@Override
	public Object getItem(int position) {
		return mListTitles.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_goods_sort_second, null);
			holder.txtSortName = (TextView)convertView.findViewById(R.id.txtSortName);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		if(position == selectIndex){
			holder.txtSortName.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
		}else{
			holder.txtSortName.setTextColor(ContextCompat.getColor(mContext, R.color.loginEditFontColor));
		}
		holder.txtSortName.setText(mListTitles.get(position).getBrand_name());
		return convertView;
	}

	private static class ViewHolder {
		private TextView txtSortName ;
	}
	public void setSelectIndex(int i){
		selectIndex = i;
	}
}