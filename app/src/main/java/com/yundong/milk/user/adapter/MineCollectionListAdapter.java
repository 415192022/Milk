package com.yundong.milk.user.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yundong.milk.R;
import com.yundong.milk.user.model.GoodsListModel;
import com.yundong.milk.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lj on 2016/11/17.
 *  我的收藏
 */
public class MineCollectionListAdapter extends RecyclerView.Adapter<MineCollectionListAdapter.GoodsHolder>{

    private HashMap<Integer, Integer> mSizeList = new HashMap<>();
    private ArrayList<GoodsListModel> mList;
    private Context mContext;
    public boolean mIsShowBox = false; //是否显示单选框,默认false
    private Map<Integer, Boolean> map = new HashMap<>(); // 存储勾选框状态的map集合
    private TextView mTextView;

    public MineCollectionListAdapter(Context context, TextView textView) {
        this.mContext = context;
        this.mTextView = textView;
        initMap();
    }

    //设置是否显示CheckBox
    public void setShowBox() {
        mIsShowBox = !mIsShowBox;
    }

    //点击item选中CheckBox
    public void setSelectItem(int position) {
        //对当前状态取反
        if (map.get(position)) {
            map.put(position, false);
        } else {
            map.put(position, true);
        }
        notifyItemChanged(position);
    }

    //返回集合给MainActivity
    public Map<Integer, Boolean> getMap() {
        return map;
    }

    public void addData(ArrayList<GoodsListModel> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public int getCheckListSize(){
        return mSizeList.size();
    }

    public void removeCheckList(){
        mSizeList.clear();
    }


    //初始化map集合,默认为不选中
    public void initMap() {
//        for (int i = 0; i < mList.size(); i++) {
        for (int i = 0; i < 20; i++) {
            map.put(i, false);
        }
    }

    @Override
    public GoodsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine_collection, parent, false);
        return new GoodsHolder(view);
    }

    @Override
    public void onBindViewHolder(final GoodsHolder holder, final int position) {
        Glide.with(mContext).load(R.mipmap.img_test).into(holder.imgCollectionPic);
        holder.txtCollectionContent.setText("房管局色粉hi粉红色的解放后几号付款的叫声");
        holder.txtPrice.setText("￥199.00");
        holder.txtCollectionNum.setText("月售2831");
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.list_anim);
        if (mIsShowBox) {
            holder.checkDelete.setVisibility(View.VISIBLE);
            holder.imgCollectionPic.startAnimation(animation);
            holder.checkDelete.startAnimation(animation);
        } else {
            holder.checkDelete.setVisibility(View.GONE);
        }
//        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.list_anim);
//        if (mIsShowBox) {
//            holder.imgCollectionPic.startAnimation(animation);
//            holder.checkDelete.startAnimation(animation);
//        }
        //设置checkBox改变监听
        holder.checkDelete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //用map集合保存
                ToastUtil.showShortToast("   " + position);
                map.put(position, isChecked);
                if (isChecked) {
//                    mSizeList.add(position);
                    mSizeList.put(position, position);
                }else {
                    mSizeList.remove(position);
                }
                if (mSizeList.size() > 0){
                    mTextView.setText(R.string.delete);
                }else {
                    mTextView.setText(R.string.edit);
                }
            }
        });
        // 设置CheckBox的状态
        if (map.get(position) == null) {
            map.put(position, false);
        }
        holder.checkDelete.setChecked(map.get(position));
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class GoodsHolder extends RecyclerView.ViewHolder {

        private ImageView imgCollectionPic;
        private TextView txtCollectionContent;
        private TextView txtPrice;
        private TextView txtCollectionNum;
        private CheckBox checkDelete;

        public GoodsHolder(View itemView) {
            super(itemView);
            imgCollectionPic = (ImageView) itemView.findViewById(R.id.imgCollectionPic);
            txtCollectionContent = (TextView) itemView.findViewById(R.id.txtCollectionContent);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            txtCollectionNum = (TextView) itemView.findViewById(R.id.txtCollectionNum);
            checkDelete = (CheckBox) itemView.findViewById(R.id.checkDelete);
        }
    }
}