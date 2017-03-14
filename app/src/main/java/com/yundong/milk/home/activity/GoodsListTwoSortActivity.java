package com.yundong.milk.home.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.home.adapter.GoodsFirstSortAdapter;
import com.yundong.milk.home.adapter.GoodsSecondSortAdapter;
import com.yundong.milk.home.adapter.GoodsSortListTwoAdapter;
import com.yundong.milk.model.RecommentTypeBean;
import com.yundong.milk.model.TypeBrandBean;
import com.yundong.milk.model.TypeGoodsBean;
import com.yundong.milk.present.GoodsListTwoSortActivityPresenter;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.util.rxbus.RxBus;
import com.yundong.milk.util.rxbus.Subscribe;
import com.yundong.milk.util.rxbus.ThreadMode;
import com.yundong.milk.view.IAllTypeView;
import com.yundong.milk.view.ITypeBrandView;
import com.yundong.milk.view.ITypeGoodsView;
import com.yundong.milk.widget.HorizontalListView;
import com.yundong.milk.widget.recyclerview.XRecyclerView;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by lj on 2017/1/19.
 * 所有分类 分类品牌  分类商品
 */
public class GoodsListTwoSortActivity extends BaseActivity
        implements
        IAllTypeView
        , ITypeBrandView
        , ITypeGoodsView {
    private HorizontalListView mFirstSortListView;
    private ListView mSecondSortListView;
    private RecyclerView mRecyclerViewGoodsList;
    private GoodsSortListTwoAdapter mGoodsListAdapter;

    private GoodsListTwoSortActivityPresenter goodsListTwoSortActivityPresenter;

    @Override
    protected void onStart() {
        super.onStart();
        RxBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getDefault().unRegister(this);
    }

    private RecommentTypeBean rtb;

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveCommentType(RecommentTypeBean recommentTypeBean) {
        this.rtb = recommentTypeBean;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list_two_sort);
        initTitle(R.string.commodity, true);
        mFirstSortListView = (HorizontalListView) findViewById(R.id.firstSortListView);
        mSecondSortListView = (ListView) findViewById(R.id.secondSortListView);
        mRecyclerViewGoodsList = (RecyclerView) findViewById(R.id.recyclerViewGoodsList);
        mRecyclerViewGoodsList.setHasFixedSize(true);
        mRecyclerViewGoodsList.setLayoutManager(new LinearLayoutManager(this));

        goodsListTwoSortActivityPresenter = GoodsListTwoSortActivityPresenter.getInstance().with(this, this, this);
        goodsListTwoSortActivityPresenter.getAllType();


        mGoodsListAdapter = new GoodsSortListTwoAdapter(GoodsListTwoSortActivity.this);
        mRecyclerViewGoodsList.setAdapter(mGoodsListAdapter);
        secondSortAdapter = new GoodsSecondSortAdapter(GoodsListTwoSortActivity.this, typeBrands);
        firstSortAdapter = new GoodsFirstSortAdapter(GoodsListTwoSortActivity.this, allTypes);
    }


    private ArrayList<RecommentTypeBean> allTypes = new ArrayList<RecommentTypeBean>();
    int position = 0;


    //第一级适配器
    GoodsFirstSortAdapter firstSortAdapter;

    @Override
    public void getAllType(final RecommentTypeBean recommentTypeBean) {
        Observable.from(recommentTypeBean.getDatas())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RecommentTypeBean>() {
                    @Override
                    public void onCompleted() {
                        // 初始化第一级分类数据
                        mFirstSortListView.setAdapter(firstSortAdapter);
                        typeBrands.clear();
//                        mFirstSortListView.setSelection(0);
                        goodsListTwoSortActivityPresenter.getTypeBrand(recommentTypeBean.getDatas().get(0).getGc_id());
                        firstSortAdapter.setSelectIndex(0);
                        firstSortAdapter.notifyDataSetChanged();
                        if (null != rtb) {
                            typeBrands.clear();
                            currentClsId = rtb.getPosition();
//                            mFirstSortListView.setSelection(rtb.getPosition());
                            goodsListTwoSortActivityPresenter.getTypeBrand(rtb.getGc_id());
                            firstSortAdapter.setSelectIndex(rtb.getPosition());
                            firstSortAdapter.notifyDataSetChanged();
                        }

                        mFirstSortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                secondSortAdapter.getmListTitles().clear();
                                typeBrands.clear();
                                currentClsId = i;
                                goodsListTwoSortActivityPresenter.getTypeBrand(recommentTypeBean.getDatas().get(i).getGc_id());
                                firstSortAdapter.setSelectIndex(i);
//                                mFirstSortListView.setSelection(i);
                                firstSortAdapter.notifyDataSetChanged();
                                secondSortAdapter.notifyDataSetChanged();
                            }
                        });

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RecommentTypeBean recommentTypeBean) {
                        allTypes.add(recommentTypeBean);
                    }
                });

    }

    @Override
    public void getAllTypeOnError(String e) {
        ToastUtil.showShortToast("获取一级分类失败。");
    }

    private ArrayList<TypeBrandBean> typeBrands = new ArrayList<TypeBrandBean>();

    //第二级适配器
    GoodsSecondSortAdapter secondSortAdapter;

    @Override
    public void getTypeBrand(TypeBrandBean typeBrandBean) {
        Observable.from(typeBrandBean.getDatas())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TypeBrandBean>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        mGoodsListAdapter.getData().clear();
                    }

                    @Override
                    public void onCompleted() {
//                        初始化第二级分类
                        mSecondSortListView.setAdapter(secondSortAdapter);
                        secondSortAdapter.setSelectIndex(0);

                        mSecondSortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                secondSortAdapter.setSelectIndex(i);
                                secondSortAdapter.notifyDataSetChanged();
                                mGoodsListAdapter.getData().clear();
                                goodsListTwoSortActivityPresenter.getTypeGoods(allTypes.get(currentClsId).getGc_id(), typeBrands.get(i).getBrand_id(), "1", "20");
                            }
                        });

                        mGoodsListAdapter.getData().clear();
                        Observable.just("")
                                .observeOn(Schedulers.io())
                                .subscribeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Subscriber<String>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onNext(String s) {
                                        goodsListTwoSortActivityPresenter.getTypeGoods(allTypes.get(currentClsId).getGc_id(), typeBrands.get(0).getBrand_id(), "1", "20");
                                    }
                                });

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TypeBrandBean typeBrandBean) {
                        typeBrands.add(typeBrandBean);
                    }
                });

    }

    private int currentClsId;

    @Override
    public void getTypeBrandOnError(String e) {
        ToastUtil.showShortToast("获得分类品牌错误");
    }

    @Override
    public void getTypeGoods(final TypeGoodsBean typeGoodsBean) {
        if (typeGoodsBean.getData().getTypeGoodsBeanDataAData().size() > 0) {
            Observable.from(typeGoodsBean.getData().getTypeGoodsBeanDataAData())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<TypeGoodsBean.TypeGoodsBeanDataO.TypeGoodsBeanDataA>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(TypeGoodsBean.TypeGoodsBeanDataO.TypeGoodsBeanDataA typeGoodsBeanDataA) {
                            mGoodsListAdapter.addData(typeGoodsBeanDataA);
                        }
                    });
        } else {
            mGoodsListAdapter.getData().clear();
            mGoodsListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getTypeGoodsOnError(String e) {
        ToastUtil.showShortToast("获取分类商品失败" + e);
    }
}
