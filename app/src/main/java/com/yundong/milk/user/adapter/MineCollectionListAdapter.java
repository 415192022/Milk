package com.yundong.milk.user.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yundong.milk.R;
import com.yundong.milk.cart.activity.GoodsDetailActivity;
import com.yundong.milk.model.MyCollectionBean;
import com.yundong.milk.user.activity.MineCollectionActivity;
import com.yundong.milk.user.model.GoodsListModel;
import com.yundong.milk.util.ToastUtil;
import com.zzhoujay.richtext.RichText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lj on 2016/11/17.
 * 我的收藏
 */
public class MineCollectionListAdapter extends RecyclerView.Adapter<MineCollectionListAdapter.GoodsHolder> {

    private HashMap<Integer, Integer> mSizeList = new HashMap<>();
    private ArrayList<String> checkedList = new ArrayList<>();
    private ArrayList<MyCollectionBean.MyCollectionBeanData.MyCollectionBeanDataArray> myCollectionBeanDataArrays = new ArrayList<>();
    private Context mContext;
    public boolean mIsShowBox = false; //是否显示单选框,默认false
    private Map<Integer, Boolean> map = new HashMap<>(); // 存储勾选框状态的map集合
    private TextView mTextView;

    public ArrayList<MyCollectionBean.MyCollectionBeanData.MyCollectionBeanDataArray> getMyCollectionBeanDataArrays() {
        return myCollectionBeanDataArrays;
    }

    public ArrayList<String> getCheckedList() {
        return checkedList;
    }

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

    public void addData(ArrayList<MyCollectionBean.MyCollectionBeanData.MyCollectionBeanDataArray> list) {
        myCollectionBeanDataArrays.addAll(list);
        notifyDataSetChanged();
    }

    public int getCheckListSize() {
        return mSizeList.size();
    }

    public void removeCheckList() {
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
        Glide.with(mContext).load(myCollectionBeanDataArrays.get(position).getGoods_main_image()).into(holder.imgCollectionPic);
        RichText.from(myCollectionBeanDataArrays.get(position).getGoods_text())
                .clickable(false)
                .into(holder.txtCollectionContent);
        holder.txtPrice.setText("￥" + myCollectionBeanDataArrays.get(position).getGoods_price());
        holder.txtCollectionNum.setText("月售" + myCollectionBeanDataArrays.get(position).getGoods_salenum());
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
                map.put(position, isChecked);
                if (isChecked) {
//                    mSizeList.add(position);
                    mSizeList.put(position, position);
                    checkedList.add(position + "");
                } else {
                    mSizeList.remove(position);
                    checkedList.remove(position+"");
                }
                if (mSizeList.size() > 0) {
                    mTextView.setText(R.string.delete);
                } else {
                    mTextView.setText(R.string.edit);
                }
            }
        });
        // 设置CheckBox的状态
        if (map.get(position) == null) {
            map.put(position, false);
        }
        holder.checkDelete.setChecked(map.get(position));
        holder.rl_item_collection_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, GoodsDetailActivity.class);
                        intent.putExtra("GOODS_ID",myCollectionBeanDataArrays.get(position).getGoods_id());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myCollectionBeanDataArrays.size();
    }

    public static class GoodsHolder extends RecyclerView.ViewHolder {

        private ImageView imgCollectionPic;
        private TextView txtCollectionContent;
        private TextView txtPrice;
        private TextView txtCollectionNum;
        private CheckBox checkDelete;
        private RelativeLayout rl_item_collection_root;

        public GoodsHolder(View itemView) {
            super(itemView);
            imgCollectionPic = (ImageView) itemView.findViewById(R.id.imgCollectionPic);
            txtCollectionContent = (TextView) itemView.findViewById(R.id.txtCollectionContent);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            txtCollectionNum = (TextView) itemView.findViewById(R.id.txtCollectionNum);
            checkDelete = (CheckBox) itemView.findViewById(R.id.checkDelete);
            rl_item_collection_root = (RelativeLayout) itemView.findViewById(R.id.rl_item_collection_root);
        }
    }
}