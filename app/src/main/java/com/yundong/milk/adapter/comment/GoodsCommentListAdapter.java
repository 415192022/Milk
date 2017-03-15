package com.yundong.milk.adapter.comment;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yundong.milk.R;
import com.yundong.milk.model.GoodsCommentListBean;
import com.yundong.milk.user.adapter.AdapterCommentPhoto;
import com.yundong.milk.util.TimeUtils;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.widget.CircleImageView;

import java.util.ArrayList;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public class GoodsCommentListAdapter extends RecyclerView.Adapter<GoodsCommentListAdapter.GoodsCommentListHolder> {

    private ArrayList<GoodsCommentListBean.DataBeanX._$0Bean.DataBean> goodsCommentListDataArrays = new ArrayList<>();
    private Context mContext;

    public ArrayList<GoodsCommentListBean.DataBeanX._$0Bean.DataBean> getGoodsCommentListDataArrays() {
        return goodsCommentListDataArrays;
    }

    public GoodsCommentListAdapter(Context context) {
        this.mContext = context;
    }

    public void addData(ArrayList<GoodsCommentListBean.DataBeanX._$0Bean.DataBean> list) {
        goodsCommentListDataArrays.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public GoodsCommentListAdapter.GoodsCommentListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_comment_list, parent, false);
        return new GoodsCommentListAdapter.GoodsCommentListHolder(view);
    }


    @Override
    public void onBindViewHolder(final GoodsCommentListAdapter.GoodsCommentListHolder holder, final int position) {
        holder.tv_comment_list_content.setText(goodsCommentListDataArrays.get(position).getComment_content());
        holder.tv_comment_list_uname.setText(goodsCommentListDataArrays.get(position).getComment_frommembername());
        holder.tv_comment_list_time.setText(TimeUtils.getTimeString(Long.decode(goodsCommentListDataArrays.get(position).getComment_addtime())));
        holder.rv_comment_images.setHasFixedSize(true);
        holder.rv_comment_images.setLayoutManager(new GridLayoutManager(mContext, 4));
        AdapterCommentListImages adapterCommentListImages = new AdapterCommentListImages(mContext);
        holder.rv_comment_images.setAdapter(adapterCommentListImages);
        for (String s : goodsCommentListDataArrays.get(position).getComment_image()) {
            adapterCommentListImages.getImages().add(s);
            adapterCommentListImages.notifyDataSetChanged();
        }
        Glide.with(mContext).load(goodsCommentListDataArrays.get(position).getAvatar()).into(holder.civ_comment_list_head);
    }

    @Override
    public int getItemCount() {
        return goodsCommentListDataArrays.size();
    }


    public static class GoodsCommentListHolder extends RecyclerView.ViewHolder {

        private CircleImageView civ_comment_list_head;
        private TextView tv_comment_list_uname;
        private TextView tv_comment_list_time;
        private TextView tv_comment_list_content;
        private RecyclerView rv_comment_images;

        public GoodsCommentListHolder(View itemView) {
            super(itemView);
            civ_comment_list_head = (CircleImageView) itemView.findViewById(R.id.civ_comment_list_head);
            tv_comment_list_uname = (TextView) itemView.findViewById(R.id.tv_comment_list_uname);
            tv_comment_list_time = (TextView) itemView.findViewById(R.id.tv_comment_list_time);
            tv_comment_list_content = (TextView) itemView.findViewById(R.id.tv_comment_list_content);
            rv_comment_images = (RecyclerView) itemView.findViewById(R.id.rv_comment_images);
        }
    }
}
