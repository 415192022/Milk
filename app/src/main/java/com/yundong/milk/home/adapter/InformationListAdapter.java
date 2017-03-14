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
import com.yundong.milk.model.LettersBean;
import com.yundong.milk.user.model.GoodsListModel;
import com.yundong.milk.util.rxbus.RxBus;

import java.util.ArrayList;

/**
 * Created by lj on 2016/11/17.
 *  我的收藏
 */
public class InformationListAdapter extends RecyclerView.Adapter<InformationListAdapter.GoodsHolder> {

    private ArrayList<LettersBean.LettersDataO.LettersDataA> lettersDataAs=new ArrayList<>();
    private Activity mContext;

    public InformationListAdapter(Activity context) {
        this.mContext = context;
    }

    public ArrayList<LettersBean.LettersDataO.LettersDataA> getLettersDataAs() {
        return lettersDataAs;
    }

    public void addData(ArrayList<LettersBean.LettersDataO.LettersDataA> list) {
        lettersDataAs.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public GoodsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_milk_information, parent, false);
        return new GoodsHolder(view);
    }

    @Override
    public void onBindViewHolder(final GoodsHolder holder, final int position) {
        Glide.with(mContext).load(lettersDataAs.get(position).getArticle_img()).into(holder.imgPic);
        holder.txtName.setText(lettersDataAs.get(position).getArticle_title());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.getDefault().post(lettersDataAs.get(position));
                mContext.startActivity(new Intent(mContext, InformationDetailActivity.class));
            }
        });
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return lettersDataAs.size();
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