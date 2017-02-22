package com.yundong.milk.user.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yundong.milk.R;

/**
 * Created by lj on 2017/1/3.
 * 消息中心
 */
public class SystemMsgAdapter extends RecyclerView.Adapter<SystemMsgAdapter.MessageCenterHolder> implements View.OnClickListener {

    private Context mContext;

    public SystemMsgAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public MessageCenterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_system_msg, parent, false);
        return new MessageCenterHolder(view);
    }

    @Override
    public void onBindViewHolder(final MessageCenterHolder holder, final int position) {

        holder.txtTitle.setText("恭喜加入牛奶之家");
        holder.txtTime.setText("08-20");
        holder.txtContent.setText("恭喜您加入牛奶之家, 我们给你准备了一份大礼, 去看看吧");
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                break;
        }
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