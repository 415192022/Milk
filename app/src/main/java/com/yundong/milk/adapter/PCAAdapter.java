package com.yundong.milk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yundong.milk.R;
import com.yundong.milk.model.PCABean;
import com.yundong.milk.present.PerfectAddressActivityPresenter;

import java.util.ArrayList;

/**
 * Created by MingweiLi on 2017/3/10.
 */

public class PCAAdapter extends RecyclerView.Adapter<PCAAdapter.WheelViewHolder> {
    private ArrayList<PCABean.PCAData> pcaDatas = new ArrayList<>();
    private Context context;
    public ArrayList<PCABean.PCAData> getPcaDatas() {
        return pcaDatas;
    }


    public PCAAdapter(Context context ) {
        this.context = context;
    }

    @Override
    public WheelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_wheel_view, parent, false);
        return new WheelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WheelViewHolder holder, final int position) {
        holder.tv_wheel_name.setText(pcaDatas.get(position).getArea_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListner.onItemClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pcaDatas.size();
    }

    public class WheelViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_wheel_name;

        public WheelViewHolder(View itemView) {
            super(itemView);
            tv_wheel_name = (TextView) itemView.findViewById(R.id.tv_wheel_name);
        }
    }

    private OnItemClickListner onItemClickListner;
    public interface OnItemClickListner{
        void onItemClick(View view,int position);
    }
    public void setOnItemClickListner(OnItemClickListner onItemClickListner){
        this.onItemClickListner=onItemClickListner;
    }
}
