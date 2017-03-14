package com.yundong.milk.user.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.imagechoose.ChooseImage;
import com.yundong.milk.imagechoose.MultiImageSelectorActivity;
import com.yundong.milk.imagechoose.crop.HDApp;
import com.yundong.milk.manager.YunDongApplication;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.OrderListBean;
import com.yundong.milk.model.send.CommentImageBean;
import com.yundong.milk.present.ActivityReturnGoodsPresenter;
import com.yundong.milk.util.Base64Utils;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.util.rxbus.RxBus;
import com.yundong.milk.util.rxbus.Subscribe;
import com.yundong.milk.util.rxbus.ThreadMode;
import com.yundong.milk.view.IReturnGoodsView;

import java.util.ArrayList;

/**
 * Created by lj on 2016/11/20.
 * 退款售后
 */
public class ActivityReturnGoods extends BaseActivity
        implements
        View.OnClickListener,
        IReturnGoodsView {
    private ImageView iv_return_goods;
    private TextView tv_CompanyName;
    private ArrayList<String> mSelectPath;
    private long timeStamp = 0;
    private String picturePath_All;
    private String picturePath_rootDirectory;
    private static final int REQUEST_IMAGE = 2;

    private ActivityReturnGoodsPresenter activityReturnGoodsPresenter;

    private OrderListBean.OrderListData.OrderListDataArray orderListDataArray = null;

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

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveOrderInfo(OrderListBean.OrderListData.OrderListDataArray orderListDataArray) {
        this.orderListDataArray = orderListDataArray;
        tv_CompanyName.setText("￥"+Float.parseFloat(orderListDataArray.getGoods_sum())*Float.parseFloat(orderListDataArray.getGoods_price()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_return_goods_note);
        initTitle("退款售后", true);
        findViewById(R.id.btn_commit_refund).setOnClickListener(this);
        iv_return_goods = (ImageView) findViewById(R.id.iv_return_goods);
        iv_return_goods.setOnClickListener(this);

        tv_CompanyName= (TextView) findViewById(R.id.tv_CompanyName);

        activityReturnGoodsPresenter = ActivityReturnGoodsPresenter.getInstance().with(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_commit_refund:
                String content = ((EditText) findViewById(R.id.editContent)).getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    ToastUtil.showShortToast(getString(R.string.please_input_feed_back_content));
                } else {
                    if (images.size() > 0) {
                        CommentImageBean commentImageBean = new CommentImageBean();
                        ArrayList<String> ig = new ArrayList<String>();
                        ig.add(Base64Utils.bitmapToBase64(images.get(0)));
                        commentImageBean.setImages(ig);
                        String json = new Gson().toJson(commentImageBean);
                        if (null != orderListDataArray && images.size() > 0) {
                            activityReturnGoodsPresenter.returnGoods(
                                    YunDongApplication.getLoginBean().getData().getUserinfo().getId(),
                                    orderListDataArray.getOrder_id(),
                                    content,
                                    json
                            );
                        }
                    }
                }
                break;
            case R.id.iv_return_goods:
                ChooseImage.jumpSelectImage(this, REQUEST_IMAGE);
                break;
        }
    }


    ArrayList<Bitmap> images = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                String url = mSelectPath.get(0);
                picturePath_All = url;
                Bitmap loacalBitmap = ChooseImage.getSmallBitmap(url);
                iv_return_goods.setImageBitmap(loacalBitmap);
                images.clear();
                images.add(loacalBitmap);
                iv_return_goods.setBackground(null);
                String spStr[] = mSelectPath.get(0).split("/");
                picturePath_rootDirectory = spStr[spStr.length - 1];
                timeStamp = System.currentTimeMillis();
            } else {
                if (HDApp.getInstance().getSingleChooseFile() != null && HDApp.getInstance().getSingleChooseFile().getTotalSpace() > 0) {
                    String path = HDApp.getInstance().getSingleChooseFile().getAbsolutePath();
                    picturePath_All = path;
                    String spStr[] = path.split("/");
                    picturePath_rootDirectory = spStr[spStr.length - 1];
                    timeStamp = System.currentTimeMillis();
                    Bitmap loacalBitmap = ChooseImage.getSmallBitmap(path);
                    iv_return_goods.setImageBitmap(loacalBitmap);
                    images.clear();
                    images.add(loacalBitmap);
                    iv_return_goods.setBackground(null);
                }
            }
        }
    }

    @Override
    public void returnGoods(BaseReceiveBean baseReceiveBean) {
        if(baseReceiveBean.getCode().equals("2000")){
            finish();
        }
    }

    @Override
    public void returnGoodsOnError(String e) {
        ToastUtil.showLongToast(e);
    }
}
