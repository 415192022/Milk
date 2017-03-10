package com.yundong.milk.cart.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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

import com.bumptech.glide.Glide;
import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.cart.adapter.GoodsDetailAttrAdapter;
import com.yundong.milk.cart.adapter.RecommendGoodsListAdapter;
import com.yundong.milk.manager.YunDongApplication;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.GoodsAndCountBean;
import com.yundong.milk.model.GoodsClassCommentBean;
import com.yundong.milk.model.GoodsDetailsBean;
import com.yundong.milk.present.GoodsDetailActivityPresenter;
import com.yundong.milk.user.activity.ActivityCommentList;
import com.yundong.milk.util.RxBusUtil;
import com.yundong.milk.util.ShareUtil;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.util.rxbus.RxBus;
import com.yundong.milk.util.rxbus.Subscribe;
import com.yundong.milk.util.rxbus.ThreadMode;
import com.yundong.milk.view.IAddCarView;
import com.yundong.milk.view.IGoodsClassCommentView;
import com.yundong.milk.view.IGoodsCollectonView;
import com.yundong.milk.view.IGoodsDetailsView;
import com.yundong.milk.view.IIsCollectionGoodsView;
import com.yundong.milk.widget.CircleImageView;
import com.yundong.milk.widget.CircleIndicator;
import com.yundong.milk.widget.recyclerview.XRecyclerView;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lj on 2017/1/5.
 */
public class GoodsDetailActivity extends BaseActivity
        implements
        IGoodsDetailsView
        , IGoodsCollectonView
        , IIsCollectionGoodsView
        , IAddCarView
        , IGoodsClassCommentView

{

    private TextView mTxtGoodsDetail, mTxtGoodsAttrs, mTxtRecommendGoods, txtCommentMore;
    private View mLineOne, mLineTwo, mLineThree;
    private LinearLayout mGoodsAttr, mLineRecommendGoods;
    private ImageView mImgGoodsDetailPic;
    private ImageView imgGoodsPic;
    private XRecyclerView mRecyclerView;
    private RecommendGoodsListAdapter mAdapter;
    private PopupWindow mPopupWindow;
    private GoodsDetailAttrAdapter mGoodsAttrAdapter;
    private TextView mTxtCollection;
    private TextView txtNowPrice;
    private TextView txtOriginalPrice;
    private TextView txtSellNum;
    private TextView txtGoodsTitle;
    private TextView txtGoodsBrief;
    private TextView txtCommentNum;
    private TextView txtCriticName;
    private CircleImageView imgCriticHead;
    private TextView txtCommentTime;
    private TextView txtCommentContent;
    private ViewPager guide_pager;
    private CircleIndicator cid_banner;

    private EditText mEditGoodsNum;
    private boolean mIsCollect = true;
    private int mGoodsNum = 1;
    private int mType = -1; //0 加入购物车  1 立即购买

    private GoodsDetailActivityPresenter goodsDetailActivityPresenter;

    private String currentGoodsId;

    public void receiveIntentData() {
        currentGoodsId = getIntent().getStringExtra("GOODS_ID");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        receiveIntentData();
        setContentView(R.layout.activity_goods_detail);
        findViewById(R.id.imageLeft).setOnClickListener(this);
        txtCommentMore = (TextView) findViewById(R.id.txtCommentMore);
        mTxtGoodsDetail = (TextView) findViewById(R.id.txtGoodsDetail);
        mTxtGoodsAttrs = (TextView) findViewById(R.id.txtGoodsAttrs);
        mTxtRecommendGoods = (TextView) findViewById(R.id.txtRecommendGoods);
        mGoodsAttr = (LinearLayout) findViewById(R.id.goodsAttr);
        mImgGoodsDetailPic = (ImageView) findViewById(R.id.imgGoodsDetailPic);
        imgGoodsPic = (ImageView) findViewById(R.id.imgGoodsPic);
        mLineRecommendGoods = (LinearLayout) findViewById(R.id.lineRecommendGoods);

        txtNowPrice = (TextView) findViewById(R.id.txtNowPrice);
        txtSellNum = (TextView) findViewById(R.id.txtSellNum);
        txtGoodsTitle = (TextView) findViewById(R.id.txtGoodsTitle);
        txtGoodsBrief = (TextView) findViewById(R.id.txtGoodsBrief);
        txtCommentNum = (TextView) findViewById(R.id.txtCommentNum);
        txtCriticName = (TextView) findViewById(R.id.txtCriticName);
        imgCriticHead= (CircleImageView) findViewById(R.id.imgCriticHead);
        txtCommentTime = (TextView) findViewById(R.id.txtCommentTime);
        txtCommentContent = (TextView) findViewById(R.id.txtCommentContent);


        guide_pager = (ViewPager) findViewById(R.id.guide_pager);
        guide_pager.setAdapter(pagerAdapter);
        cid_banner = (CircleIndicator) findViewById(R.id.cid_banner);
        cid_banner.setViewPager(guide_pager);

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
        txtOriginalPrice = ((TextView) findViewById(R.id.txtOriginalPrice));
        // 设置中划线并加清晰
        txtOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        goodsDetailActivityPresenter = GoodsDetailActivityPresenter.getInstance().with(this, this, this, this, this);
        goodsDetailActivityPresenter.getGoodsDetails(currentGoodsId);
        goodsDetailActivityPresenter.isCollectionGoods(YunDongApplication.getLoginBean().getData().getUserinfo().getId(), currentGoodsId);

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.txtCommentMore:
                RxBus.getDefault().post(goodsDetailsBean);
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
                //查询类似商品推荐
                goodsDetailActivityPresenter.getGoodsClassComment(goodsDetailsBean.getData().getGoods_type(), "20", "1");

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
                if (mType == 0) { //加入购物车
                    mPopupWindow.dismiss();
                    goodsDetailActivityPresenter.addCar(YunDongApplication.getLoginBean().getData().getUserinfo().getId(), currentGoodsId, String.valueOf(mGoodsNum));
                } else if (mType == 1) { //立即购买
                    mPopupWindow.dismiss();

                    //ConfirmOrderActivity -> receiveGoodsInfo
                    String count = mEditGoodsNum.getText() + "";
                    GoodsAndCountBean goodsAndCountBean = new GoodsAndCountBean();
                    goodsAndCountBean.setGoodsDetailsBean(goodsDetailsBean);
                    goodsAndCountBean.setCount(count);
                    goodsAndCountBean.setTotlePrice(String.valueOf(mGoodsNum * Float.parseFloat(goodsDetailsBean.getData().getGoods_price())));
                    RxBus.getDefault().post(goodsAndCountBean);
                    Intent intent = new Intent(GoodsDetailActivity.this, ConfirmOrderActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.imgCancelPop:
                mPopupWindow.dismiss();
                break;
            case R.id.txtCollection://收藏
                goodsDetailActivityPresenter.goodsCollection(YunDongApplication.getLoginBean().getData().getUserinfo().getId(), currentGoodsId);
                break;
            case R.id.reGoodsMoreComment://更多评论
                startActivity(new Intent(this, SeeAllCommentActivity.class).putExtra("goods_id", getIntent().getStringExtra("goodsId")));
                break;
        }
    }


    private float price = 553.33f;
    private TextView txtGoodsAttrPrice;
    private TextView txtAttrPrice;

    public void showPop() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.pop_goods_attr, null);
        contentView.findViewById(R.id.imgCancelPop).setOnClickListener(this);
        contentView.findViewById(R.id.txtComplete).setOnClickListener(this);
        ImageView imgGoodsPic = (ImageView) contentView.findViewById(R.id.imgGoodsPic);
        TextView tv_goods_type = (TextView) contentView.findViewById(R.id.tv_goods_type);
        tv_goods_type.setText("商品类别:" + goodsDetailsBean.getData().getGoods_type_name());
        Glide.with(this).load(goodsDetailsBean.getData().getGoods_main_image()).into(imgGoodsPic);
        txtGoodsAttrPrice = (TextView) contentView.findViewById(R.id.txtGoodsAttrPrice);
        txtGoodsAttrPrice.setText(goodsDetailsBean.getData().getGoods_price());
        txtAttrPrice = (TextView) contentView.findViewById(R.id.txtAttrPrice);
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
                if (mGoodsNum <= 1) {
                    mGoodsNum = 1;
                }
                txtAttrPrice.setText("¥ " + String.valueOf(mGoodsNum * Float.parseFloat(goodsDetailsBean.getData().getGoods_price())));
                mEditGoodsNum.setText(String.valueOf(mGoodsNum));
            }
        });
        contentView.findViewById(R.id.btnAddNumber).setOnClickListener(new View.OnClickListener() { //增加数量
            @Override
            public void onClick(View view) {
                mGoodsNum = Integer.parseInt(mEditGoodsNum.getText().toString().trim());
                mGoodsNum++;
                txtAttrPrice.setText("¥ " + String.valueOf(mGoodsNum * Float.parseFloat(goodsDetailsBean.getData().getGoods_price())));
                mEditGoodsNum.setText(String.valueOf(mGoodsNum));

            }
        });
    }

    private GoodsDetailsBean goodsDetailsBean;

    //商品详情
    @Override
    public void getGoodsDetails(GoodsDetailsBean goodsDetailsBean) {
        this.goodsDetailsBean = goodsDetailsBean;
        //现价
        txtNowPrice.setText(goodsDetailsBean.getData().getGoods_price());
        //原价
        txtOriginalPrice.setText(goodsDetailsBean.getData().getGoods_marketprice());
        //销量
        txtSellNum.setText("累计销量:" + goodsDetailsBean.getData().getGoods_salenum());
        //名称
        txtGoodsTitle.setText(goodsDetailsBean.getData().getGoods_name());
        //简介
        txtGoodsBrief.setText(goodsDetailsBean.getData().getGoods_text());
        //商品评价
        txtCommentNum.setText(goodsDetailsBean.getData().getComment_sum());
        //评论人名
        txtCriticName.setText(goodsDetailsBean.getData().getComment().getComment_frommembername());
        //平评论人头像
        Glide.with(this).load(goodsDetailsBean.getData().getComment().getAvatar()).into(imgCriticHead);
        //评论时间
        txtCommentTime.setText(goodsDetailsBean.getData().getComment().getComment_addtime());
        //评论内容
        txtCommentContent.setText(goodsDetailsBean.getData().getComment().getComment_content());
        //banner
        Observable.from(goodsDetailsBean.getData().getGoods_image())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        View view = LayoutInflater.from(GoodsDetailActivity.this).inflate(R.layout.layout_image_banner, null, false);
                        ImageView imageView = (ImageView) view.findViewById(R.id.iv_banner);
                        Glide.with(GoodsDetailActivity.this).load(s).into(imageView);
                        viewList.add(view);
                        pagerAdapter.notifyDataSetChanged();
                    }
                });


    }

    @Override
    public void getGoodsDetailsOnError(String e) {
        ToastUtil.showShortToast(e + "加载详情出错");
    }

    ArrayList<View> viewList = new ArrayList<>();
    PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {

            return arg0 == arg1;
        }

        @Override
        public int getCount() {

            return viewList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView(viewList.get(position));

        }

        @Override
        public int getItemPosition(Object object) {

            return super.getItemPosition(object);
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));

            return viewList.get(position);
        }

    };

    //收藏
    @Override
    public void collection(BaseReceiveBean baseReceiveBean) {
        //成功
        if (baseReceiveBean.getCode().equals("2000")) {
            mTxtCollection.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.mipmap.img_collect_pressed), null, null, null);
        } else if (baseReceiveBean.getCode().equals("2002")) {
            //取消
            mTxtCollection.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.mipmap.img_collect_normal), null, null, null);
        } else if (baseReceiveBean.getCode().equals("2002")) {
            //系统错误
            ToastUtil.showShortToast("系统错误");
        }
        ToastUtil.showShortToast(baseReceiveBean.getMsg());
    }

    @Override
    public void collectionOnError(String e) {
        ToastUtil.showShortToast("添加失败！");
    }

    @Override
    public void isCollectionGoods(BaseReceiveBean baseReceiveBean) {
        //成功
        if (baseReceiveBean.getCode().equals("2000")) {
            mTxtCollection.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.mipmap.img_collect_pressed), null, null, null);
        } else if (baseReceiveBean.getCode().equals("2002")) {
            //取消
            mTxtCollection.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.mipmap.img_collect_normal), null, null, null);
        }
    }

    @Override
    public void isCollectionGoodsOnError(String e) {

    }


    //添加购物车
    @Override
    public void addCar(BaseReceiveBean baseReceiveBean) {
        if (baseReceiveBean.getCode().equals("2000")) {
            ToastUtil.showShortToast(baseReceiveBean.getMsg());
        }
    }

    @Override
    public void addCarOnError(String e) {
        ToastUtil.showShortToast("添加购物车失败");
    }

    //相似商品推荐
    @Override
    public void getGoodsClassComment(GoodsClassCommentBean goodsClassCommentBean) {
        final ArrayList<GoodsClassCommentBean.GoodsClassCommentDataO.GoodsClassCommentDataA> mList = new ArrayList<>();
        Observable.from(goodsClassCommentBean.getData().getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GoodsClassCommentBean.GoodsClassCommentDataO.GoodsClassCommentDataA>() {
                    @Override
                    public void onCompleted() {
                        mAdapter.addData(mList);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GoodsClassCommentBean.GoodsClassCommentDataO.GoodsClassCommentDataA goodsClassCommentDataA) {
                        mList.add(goodsClassCommentDataA);
                    }
                });
    }

    @Override
    public void getGoodsClassCommentOnError(String e) {
        ToastUtil.showShortToast("获得推荐商品失败");
    }
}

