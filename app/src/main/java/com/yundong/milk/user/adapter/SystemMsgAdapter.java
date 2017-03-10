package com.yundong.milk.user.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yundong.milk.R;
import com.yundong.milk.model.MessageInfoBean;
import com.yundong.milk.util.ToastUtil;

import java.util.ArrayList;

/**
 * Created by lj on 2017/1/3.
 * 消息中心
 */
public class SystemMsgAdapter extends RecyclerView.Adapter<SystemMsgAdapter.MessageCenterHolder> {

    private Context mContext;
    private ArrayList<MessageInfoBean.MessageInfoData> messageInfoBeen = new ArrayList<>();

    public SystemMsgAdapter(Context context) {
        this.mContext = context;
    }

    public ArrayList<MessageInfoBean.MessageInfoData> getMessageInfoBeen() {
        return messageInfoBeen;
    }

    @Override
    public MessageCenterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_system_msg, parent, false);
        return new MessageCenterHolder(view);
    }

    @Override
    public void onBindViewHolder(final MessageCenterHolder holder, final int position) {
//        holder.txtTitle.setText(messageInfoBeen.get(position).getMessage_title());
//        holder.txtTime.setText(messageInfoBeen.get(position).getSend_time());
//        holder.txtContent.setText(messageInfoBeen.get(position).getMessage_content());
    }

    @Override
    public int getItemCount() {
        return messageInfoBeen.size();
    }


    public static class MessageCenterHolder extends RecyclerView.ViewHolder {

        private TextView txtTitle;
        private TextView txtTime;
        private TextView txtContent;

        public MessageCenterHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtTime = (TextView) itemView.findViewById(R.id.txtTime);
            txtContent = (TextView) itemView.findViewById(R.id.txtContent);
        }
    }
}