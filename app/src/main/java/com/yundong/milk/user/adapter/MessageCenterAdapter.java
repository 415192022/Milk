package com.yundong.milk.user.adapter;

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
import com.yundong.milk.user.activity.SystemMsgActivity;

/**
 * Created by lj on 2017/1/3.
 * 消息中心
 */
public class MessageCenterAdapter extends RecyclerView.Adapter<MessageCenterAdapter.MessageCenterHolder> implements View.OnClickListener{

    private Activity mContext;

    public MessageCenterAdapter(Activity context) {
        this.mContext = context;
    }

    @Override
    public MessageCenterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_center, parent, false);
        return new MessageCenterHolder(view);
    }

    @Override
    public void onBindViewHolder(final MessageCenterHolder holder, final int position) {

        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(position);
        if (position == 0) {
            Glide.with(mContext).load(R.mipmap.img_message_center_system).into(holder.imgMsgType);
            holder.txtMsgType.setText(R.string.systemMsg);
        }else if (position == 1){
            Glide.with(mContext).load(R.mipmap.img_message_center_comment).into(holder.imgMsgType);
            holder.txtMsgType.setText(R.string.commentMsg);
        }
        Glide.with(mContext).load(R.mipmap.img_red_point).into(holder.imgRedPoint);
        holder.txtTime.setText("2017-1-3 11:51");
        holder.txtMsgBrief.setText("发的啥福建省的方式发生的粉红色的发生的纠纷开发发货的开始减肥和第三方是大方的说法水电费水电费");
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public void onClick(View view) {
        int index = (Integer) view.getTag();
        switch (view.getId()){
           default:
               if (index == 0){ //系统消息
                   mContext.startActivity(new Intent(mContext, SystemMsgActivity.class));
               }else if (index == 1){ //活动消息
                   mContext.startActivity(new Intent(mContext, SystemMsgActivity.class));
               }
                break;
        }
    }

    public static class MessageCenterHolder extends RecyclerView.ViewHolder {

        private ImageView imgMsgType;
        private TextView txtMsgType;
        private ImageView imgRedPoint;
        private TextView txtTime;
        private TextView txtMsgBrief;

        public MessageCenterHolder(View itemView) {
            super(itemView);
            imgMsgType = (ImageView) itemView.findViewById(R.id.imgMsgType);
            txtMsgType = (TextView) itemView.findViewById(R.id.txtMsgType);
            imgRedPoint = (ImageView) itemView.findViewById(R.id.imgRedPoint);
            txtTime = (TextView) itemView.findViewById(R.id.txtTime);
            txtMsgBrief = (TextView) itemView.findViewById(R.id.txtMsgBrief);
        }
    }
}