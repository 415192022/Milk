package com.yundong.milk.cart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yundong.milk.R;
import com.yundong.milk.cart.model.CommentListModel;
import com.yundong.milk.widget.CircleImageView;

import java.util.ArrayList;

/**
 * Created by lj on 2016/11/17.
 */
public class GoodsCommentListAdapter extends RecyclerView.Adapter<GoodsCommentListAdapter.GoodsCommentHolder>{

    private ArrayList<CommentListModel> mList;
    private Context mContext;

    public GoodsCommentListAdapter(Context activity) {
        this.mContext = activity;
        mList = new ArrayList<>();
    }

    public void addData(ArrayList<CommentListModel> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void removeData(){
        mList.clear();
        notifyDataSetChanged();
    }

    @Override
    public GoodsCommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_list, parent, false);
        return new GoodsCommentHolder(view);
    }

    @Override
    public void onBindViewHolder(GoodsCommentHolder holder, int position) {

        CommentListModel model = mList.get(position);
        Glide.with(mContext).load(model.avatar).placeholder(R.mipmap.img_head_default).into(holder.imgCriticHead);
        holder.txtCriticName.setText(model.username);
        holder.txtCriticClass.setText("V" + model.comment_grade);
        holder.txtCommentTime.setText(model.comment_addtime);
        holder.txtCommentContent.setText(model.comment_content);
        holder.txtGoodsAttr.setText(model.goods_attr_text);
        int num = model.comment_image.length;
        switch (num){
            case 0:
                holder.lineImg.setVisibility(View.GONE);
                break;
            case 1:
                Glide.with(mContext).load(model.comment_image[0]).into(holder.imgOne);
                break;
            case 2:
                Glide.with(mContext).load(model.comment_image[0]).into(holder.imgOne);
                Glide.with(mContext).load(model.comment_image[1]).into(holder.imgTwo);
                break;
            case 3:
                Glide.with(mContext).load(model.comment_image[0]).into(holder.imgOne);
                Glide.with(mContext).load(model.comment_image[1]).into(holder.imgTwo);
                Glide.with(mContext).load(model.comment_image[2]).into(holder.imgThree);
                break;
            case 4:
                Glide.with(mContext).load(model.comment_image[0]).into(holder.imgOne);
                Glide.with(mContext).load(model.comment_image[1]).into(holder.imgTwo);
                Glide.with(mContext).load(model.comment_image[2]).into(holder.imgThree);
                Glide.with(mContext).load(model.comment_image[3]).into(holder.imgFour);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class GoodsCommentHolder extends RecyclerView.ViewHolder {

        private CircleImageView imgCriticHead;
        private TextView txtCriticName;
        private TextView txtCriticClass;
        private TextView txtCommentTime;
        private TextView txtCommentContent;
        private TextView txtGoodsAttr;
        private ImageView imgOne, imgTwo, imgThree, imgFour;
        private LinearLayout lineImg;

        public GoodsCommentHolder(View itemView) {
            super(itemView);
            imgCriticHead = (CircleImageView) itemView.findViewById(R.id.imgCriticHead);
            txtCriticName = (TextView) itemView.findViewById(R.id.txtCriticName);
            txtCriticClass = (TextView) itemView.findViewById(R.id.txtCriticClass);
            txtCommentTime = (TextView) itemView.findViewById(R.id.txtCommentTime);
            txtCommentContent = (TextView) itemView.findViewById(R.id.txtCommentContent);
            txtGoodsAttr = (TextView) itemView.findViewById(R.id.txtGoodsAttr);
            imgOne = (ImageView) itemView.findViewById(R.id.imgOne);
            imgTwo = (ImageView) itemView.findViewById(R.id.imgTwo);
            imgThree = (ImageView) itemView.findViewById(R.id.imgThree);
            imgFour = (ImageView) itemView.findViewById(R.id.imgFour);
            lineImg = (LinearLayout) itemView.findViewById(R.id.lineImg);
        }
    }
}