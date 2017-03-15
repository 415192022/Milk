package com.yundong.milk.user.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yundong.milk.R;
import com.yundong.milk.model.MessageListBean;
import com.yundong.milk.user.activity.SystemMsgActivity;
import com.yundong.milk.util.TimeUtils;
import com.yundong.milk.util.rxbus.RxBus;

import java.util.ArrayList;

/**
 * Created by lj on 2017/1/3.
 * 消息中心
 */
public class MessageCenterAdapter extends RecyclerView.Adapter<MessageCenterAdapter.MessageCenterHolder> {

    private Activity mContext;
    private ArrayList<MessageListBean.MessageListData.MessageListDataArray> messageListDataArrays = new ArrayList<>();

    public MessageCenterAdapter(Activity context) {
        this.mContext = context;
    }

    public ArrayList<MessageListBean.MessageListData.MessageListDataArray> getMessageListDataArrays() {
        return messageListDataArrays;
    }

    @Override
    public MessageCenterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_center, parent, false);
        return new MessageCenterHolder(view);
    }

    @Override
    public void onBindViewHolder(final MessageCenterHolder holder, final int position) {
        holder.itemView.setTag(position);
        if (messageListDataArrays.get(position).getMessage_type().equals("1")) {
            Glide.with(mContext).load(R.mipmap.img_message_center_system).into(holder.imgMsgType);
        } else if (messageListDataArrays.get(position).getMessage_type().equals("2")) {
            Glide.with(mContext).load(R.mipmap.img_message_center_comment).into(holder.imgMsgType);
        }
        holder.txtMsgType.setText(messageListDataArrays.get(position).getMessage_title());
        Glide.with(mContext).load(R.mipmap.img_red_point).into(holder.imgRedPoint);
        holder.txtTime.setText(TimeUtils.getTimeString(Long.decode(messageListDataArrays.get(position).getSend_time())));
        holder.txtMsgBrief.setText(messageListDataArrays.get(position).getMessage_content());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.getDefault().post(messageListDataArrays.get(position));
                mContext.startActivity(new Intent(mContext, SystemMsgActivity.class));
            }
        });


    }

    @Override
    public int getItemCount() {
        return messageListDataArrays.size();
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