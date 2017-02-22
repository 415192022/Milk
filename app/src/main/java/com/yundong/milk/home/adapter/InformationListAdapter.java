package com.yundong.milk.home.adapter;

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
import com.yundong.milk.home.activity.InformationDetailActivity;
import com.yundong.milk.user.model.GoodsListModel;

import java.util.ArrayList;

/**
 * Created by lj on 2016/11/17.
 *  我的收藏
 */
public class InformationListAdapter extends RecyclerView.Adapter<InformationListAdapter.GoodsHolder> implements View.OnClickListener{

    private ArrayList<GoodsListModel> mList;
    private Activity mContext;

    public InformationListAdapter(Activity context) {
        this.mContext = context;
    }

    public void addData(ArrayList<GoodsListModel> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public GoodsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_milk_information, parent, false);
        return new GoodsHolder(view);
    }

    @Override
    public void onBindViewHolder(final GoodsHolder holder, final int position) {
        Glide.with(mContext).load(R.mipmap.img_test).into(holder.imgPic);
        holder.txtName.setText("发货的空间司法所发货独守空房发货的dfs思考几分喝点水发生的航空件发生");
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
           default:
               mContext.startActivity(new Intent(mContext, InformationDetailActivity.class));
                break;
        }
    }

    public static class GoodsHolder extends RecyclerView.ViewHolder {

        private ImageView imgPic;
        private TextView txtName;

        public GoodsHolder(View itemView) {
            super(itemView);
            imgPic = (ImageView) itemView.findViewById(R.id.imgPic);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
        }
    }
}