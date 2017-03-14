package com.yundong.milk.widget.popupwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yundong.milk.R;
import com.yundong.milk.adapter.PCAAdapter;
import com.yundong.milk.model.PCABean;
import com.yundong.milk.present.PerfectAddressActivityPresenter;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.view.IPCAView;
import com.yundong.milk.widget.wheelview.OnWheelChangedListener;
import com.yundong.milk.widget.wheelview.WheelView;
import com.yundong.milk.widget.wheelview.adapters.ArrayWheelAdapter;
import com.yundong.milk.widget.wheelview.adapters.WheelViewAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/3/10.
 */

public class PCAPopupWindow extends PopupWindow implements IPCAView {
    private Context mContext;

    private View view;

    private TextView btn_cancel;
    private RecyclerView rv_province;
    private RecyclerView rv_city;
    private RecyclerView rv_area;
    private PCAAdapter pAdapter;
    private PCAAdapter cAdapter;
    private PCAAdapter aAdapter;

    private PerfectAddressActivityPresenter perfectAddressActivityPresenter;


    private PCABean.PCAData province;
    private PCABean.PCAData city;
    private PCABean.PCAData area;

    private void initView(View view) {
        pAdapter = new PCAAdapter(mContext);

        cAdapter = new PCAAdapter(mContext);

        aAdapter = new PCAAdapter(mContext);
        pAdapter.setOnItemClickListner(new PCAAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(View view, int position) {
                perfectAddressActivityPresenter.getC(pAdapter.getPcaDatas().get(position).getArea_id());
                province = pAdapter.getPcaDatas().get(position);
            }
        });
        cAdapter.setOnItemClickListner(new PCAAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(View view, int position) {
                perfectAddressActivityPresenter.getA(cAdapter.getPcaDatas().get(position).getArea_id());
                city = cAdapter.getPcaDatas().get(position);
            }
        });
        aAdapter.setOnItemClickListner(new PCAAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(View view, int position) {
                area = aAdapter.getPcaDatas().get(position);
                if (null == province) {
                    ToastUtil.showShortToast("请选择省");
                } else if (null == city) {
                    ToastUtil.showShortToast("请选择市");
                } else if (null == area) {
                    ToastUtil.showShortToast("请选择区");
                } else {
                    onCompleteListenner.OnComplete(province, city, area);
                    dismiss();
                }
            }
        });
        rv_province = (RecyclerView) view.findViewById(R.id.rv_province);
        rv_province.setHasFixedSize(true);
        rv_province.setLayoutManager(new LinearLayoutManager(mContext));
        rv_province.setAdapter(pAdapter);


        rv_city = (RecyclerView) view.findViewById(R.id.rv_city);
        rv_city.setHasFixedSize(true);
        rv_city.setLayoutManager(new LinearLayoutManager(mContext));
        rv_city.setAdapter(cAdapter);


        rv_area = (RecyclerView) view.findViewById(R.id.rv_area);
        rv_area.setHasFixedSize(true);
        rv_area.setLayoutManager(new LinearLayoutManager(mContext));
        rv_area.setAdapter(aAdapter);
    }

    private OnCompleteListenner onCompleteListenner;

    public interface OnCompleteListenner {
        void OnComplete(PCABean.PCAData province, PCABean.PCAData city, PCABean.PCAData area);
    }

    public void setOnCompleteListenner(OnCompleteListenner onCompleteListenner) {
        this.onCompleteListenner = onCompleteListenner;
    }

    public PCAPopupWindow(Context mContext, final OnCompleteListenner onCompleteListenner) {
        this.onCompleteListenner=onCompleteListenner;
        this.view = LayoutInflater.from(mContext).inflate(R.layout.layout_wheel_view, null);
        this.mContext = mContext;
        initView(view);
//        btn_take_photo = (Button) view.findViewById(R.id.btn_take_photo);
//        btn_pick_photo = (Button) view.findViewById(R.id.btn_pick_photo);
        btn_cancel = (TextView) view.findViewById(R.id.btnCancel);
        // 取消按钮
        btn_cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // 销毁弹出框
                dismiss();
            }
        });
        view.findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null == province) {
                    ToastUtil.showShortToast("请选择省");
                } else if (null == city) {
                    ToastUtil.showShortToast("请选择市");
                } else if (null == area) {
                    ToastUtil.showShortToast("请选择区");
                } else {
                    onCompleteListenner.OnComplete(province, city, area);
                    dismiss();
                }
            }
        });
        // 设置按钮监听
//        btn_pick_photo.setOnClickListener(itemsOnClick);
//        btn_take_photo.setOnClickListener(itemsOnClick);

        // 设置外部可点击
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框


    /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.take_photo_anim);

        perfectAddressActivityPresenter = PerfectAddressActivityPresenter.getInstance().with(this);
        perfectAddressActivityPresenter.getP("");

    }


    //获得省
    @Override
    public void getP(PCABean pcaBean) {
        Observable.from(pcaBean.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PCABean.PCAData>() {

                    @Override
                    public void onCompleted() {
                        perfectAddressActivityPresenter.getC(pAdapter.getPcaDatas().get(0).getArea_id());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PCABean.PCAData pcaData) {
                        pAdapter.getPcaDatas().add(pcaData);
                        pAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void getPOnError(String e) {

    }

    //获得市
    @Override
    public void getC(PCABean pcaBean) {
        Observable.from(pcaBean.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PCABean.PCAData>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        cAdapter.getPcaDatas().clear();
                    }

                    @Override
                    public void onCompleted() {
                        perfectAddressActivityPresenter.getA(cAdapter.getPcaDatas().get(0).getArea_id());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PCABean.PCAData pcaData) {
                        cAdapter.getPcaDatas().add(pcaData);
                        cAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void getCOnError(String e) {

    }

    //获得区
    @Override
    public void getA(PCABean pcaBean) {
        Observable.from(pcaBean.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PCABean.PCAData>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        aAdapter.getPcaDatas().clear();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PCABean.PCAData pcaData) {
                        aAdapter.getPcaDatas().add(pcaData);
                        aAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void getAOnError(String e) {

    }
}
