package com.yundong.milk.cart.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.cart.adapter.GoodsDetailAttrAdapter;
import com.yundong.milk.cart.adapter.RecommendGoodsListAdapter;
import com.yundong.milk.imagechoose.bean.Image;
import com.yundong.milk.user.activity.ActivityCommentList;
import com.yundong.milk.util.ShareUtil;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.view.recyclerview.XRecyclerView;

/**
 * Created by lj on 2017/1/5.
 */
public class GoodsDetailActivity extends BaseActivity{

    private TextView mTxtGoodsDetail, mTxtGoodsAttrs, mTxtRecommendGoods,txtCommentMore;
    private View mLineOne, mLineTwo, mLineThree;
    private LinearLayout mGoodsAttr, mLineRecommendGoods;
    private ImageView mImgGoodsDetailPic;
    private ImageView imgGoodsPic;
    private XRecyclerView mRecyclerView;
    private RecommendGoodsListAdapter mAdapter;
    private PopupWindow mPopupWindow;
    private GoodsDetailAttrAdapter mGoodsAttrAdapter;
    private TextView mTxtCollection;

    private EditText mEditGoodsNum;
    private boolean mIsCollect = true;
    private int mGoodsNum = 1;
    private int mType = -1; //0 加入购物车  1 立即购买

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        findViewById(R.id.imageLeft).setOnClickListener(this);
        txtCommentMore= (TextView) findViewById(R.id.txtCommentMore);
        mTxtGoodsDetail = (TextView) findViewById(R.id.txtGoodsDetail);
        mTxtGoodsAttrs = (TextView) findViewById(R.id.txtGoodsAttrs);
        mTxtRecommendGoods = (TextView) findViewById(R.id.txtRecommendGoods);
        mGoodsAttr = (LinearLayout) findViewById(R.id.goodsAttr);
        mImgGoodsDetailPic = (ImageView) findViewById(R.id.imgGoodsDetailPic);
        imgGoodsPic= (ImageView) findViewById(R.id.imgGoodsPic);
        mLineRecommendGoods = (LinearLayout) findViewById(R.id.lineRecommendGoods);
        mRecyclerView = (XRecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.initParams();
        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView.setLoadingMoreEnabled(false);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new RecommendGoodsListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mLineOne = findViewById(R.id.lineOne);
        mLineTwo = findViewById(R.id.lineTwo);
        mLineThree = findViewById(R.id.lineThree);
        txtCommentMore.setOnClickListener(this);
        mTxtGoodsDetail.setOnClickListener(this);
        mTxtGoodsAttrs.setOnClickListener(this);
        mTxtRecommendGoods.setOnClickListener(this);
        findViewById(R.id.txtAddCart).setOnClickListener(this);
        findViewById(R.id.txtBuyIm).setOnClickListener(this);
        mTxtCollection = (TextView) findViewById(R.id.txtCollection);
        mTxtCollection.setOnClickListener(this);
        findViewById(R.id.imageRight).setOnClickListener(this);
        findViewById(R.id.reGoodsMoreComment).setOnClickListener(this);
        ((TextView) findViewById(R.id.txtOriginalPrice)).getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.txtCommentMore:
                startActivity(new Intent(this, ActivityCommentList.class));
                break;
            case R.id.imageLeft:
                finish();
                break;
            case R.id.imageRight: //分享
                ShareUtil.showShare(this, "", "", "", "");
                break;
            case R.id.txtGoodsDetail://商品详情
                mLineOne.setVisibility(View.VISIBLE);
                mLineTwo.setVisibility(View.INVISIBLE);
                mLineThree.setVisibility(View.INVISIBLE);
                mImgGoodsDetailPic.setVisibility(View.VISIBLE);
                mGoodsAttr.setVisibility(View.GONE);
                mLineRecommendGoods.setVisibility(View.GONE);
                mTxtGoodsDetail.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                mTxtGoodsAttrs.setTextColor(ContextCompat.getColor(this, R.color.titleColor));
                mTxtRecommendGoods.setTextColor(ContextCompat.getColor(this, R.color.titleColor));
                break;
            case R.id.txtGoodsAttrs://商品规格
                mLineOne.setVisibility(View.INVISIBLE);
                mLineTwo.setVisibility(View.VISIBLE);
                mLineThree.setVisibility(View.INVISIBLE);
                mImgGoodsDetailPic.setVisibility(View.GONE);
                mGoodsAttr.setVisibility(View.VISIBLE);
                mLineRecommendGoods.setVisibility(View.GONE);
                mTxtGoodsDetail.setTextColor(ContextCompat.getColor(this, R.color.titleColor));
                mTxtGoodsAttrs.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                mTxtRecommendGoods.setTextColor(ContextCompat.getColor(this, R.color.titleColor));
                break;
            case R.id.txtRecommendGoods://推荐商品
                mLineOne.setVisibility(View.INVISIBLE);
                mLineTwo.setVisibility(View.INVISIBLE);
                mLineThree.setVisibility(View.VISIBLE);
                mImgGoodsDetailPic.setVisibility(View.GONE);
                mGoodsAttr.setVisibility(View.GONE);
                mLineRecommendGoods.setVisibility(View.VISIBLE);
                mTxtGoodsDetail.setTextColor(ContextCompat.getColor(this, R.color.titleColor));
                mTxtGoodsAttrs.setTextColor(ContextCompat.getColor(this, R.color.titleColor));
                mTxtRecommendGoods.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                break;
            case R.id.txtBuyIm:
                mType = 1;
                showPop();
                break;
            case R.id.txtAddCart:
                mType = 0;
                showPop();
                break;
            case R.id.txtComplete:
                if (mType == 0){ //加入购物车
                    mPopupWindow.dismiss();
                    ToastUtil.showShortToast(R.string.add_shopping_cart_success);
                }else if (mType == 1){ //立即购买
                    mPopupWindow.dismiss();
                    startActivity(new Intent(this, ConfirmOrderActivity.class));
                }
                break;
            case R.id.imgCancelPop:
                mPopupWindow.dismiss();
                break;
            case R.id.txtCollection://收藏
                if (mIsCollect){
                    mTxtCollection.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.mipmap.img_collect_pressed), null, null, null);
                }else {
                    mTxtCollection.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.mipmap.img_collect_normal), null, null, null);
                }
                mIsCollect = !mIsCollect;
                break;
            case R.id.reGoodsMoreComment://更多评论
                startActivity(new Intent(this, SeeAllCommentActivity.class).putExtra("goods_id", getIntent().getStringExtra("goodsId")));
                break;
        }
    }

    private float price=553.33f;
    private TextView txtGoodsAttrPrice;
    private TextView txtAttrPrice;

    public void showPop(){
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.pop_goods_attr, null);
        contentView.findViewById(R.id.imgCancelPop).setOnClickListener(this);
        contentView.findViewById(R.id.txtComplete).setOnClickListener(this);
        txtGoodsAttrPrice= (TextView) contentView.findViewById(R.id.txtGoodsAttrPrice);
        txtAttrPrice= (TextView) contentView.findViewById(R.id.txtAttrPrice);
        mEditGoodsNum = (EditText) contentView.findViewById(R.id.editGoodsNum);
        GridView gridView = (GridView) contentView.findViewById(R.id.gridView);
        contentView.setFocusable(true);
        contentView.setFocusableInTouchMode(true);
        mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAtLocation(findViewById(R.id.imageLeft), Gravity.BOTTOM, 0, 0);


        contentView.getBackground().setAlpha(220);
        mPopupWindow.setAnimationStyle(R.style.take_photo_anim);
        mGoodsAttrAdapter = new GoodsDetailAttrAdapter(this);
        gridView.setAdapter(mGoodsAttrAdapter);
        mGoodsAttrAdapter.setSelection(0);//默认选中第一个
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mGoodsAttrAdapter.setSelection(position);
                mGoodsAttrAdapter.notifyDataSetChanged();
            }
        });
        contentView.findViewById(R.id.btnReduceNumber).setOnClickListener(new View.OnClickListener() { //减少数量
            @Override
            public void onClick(View view) {
                mGoodsNum = Integer.parseInt(mEditGoodsNum.getText().toString().trim());
                mGoodsNum--;
                if (mGoodsNum <= 1){
                    mGoodsNum = 1;
                }
                txtAttrPrice.setText("¥ "+ String.valueOf(mGoodsNum*price));
                mEditGoodsNum.setText(String.valueOf(mGoodsNum));
            }
        });
        contentView.findViewById(R.id.btnAddNumber).setOnClickListener(new View.OnClickListener() { //增加数量
            @Override
            public void onClick(View view) {
                mGoodsNum = Integer.parseInt(mEditGoodsNum.getText().toString().trim());
                mGoodsNum++;
                txtAttrPrice.setText("¥ "+String.valueOf(mGoodsNum*price));
                mEditGoodsNum.setText(String.valueOf(mGoodsNum));

            }
        });
    }
}

