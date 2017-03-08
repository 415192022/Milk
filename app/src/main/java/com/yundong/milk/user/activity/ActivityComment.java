package com.yundong.milk.user.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.manager.YunDongApplication;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.OrderListBean;
import com.yundong.milk.model.send.CommentImageBean;
import com.yundong.milk.present.ActivityCommentPresenter;
import com.yundong.milk.user.adapter.AdapterCommentPhoto;
import com.yundong.milk.util.Base64Utils;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.util.rxbus.RxBus;
import com.yundong.milk.util.rxbus.Subscribe;
import com.yundong.milk.util.rxbus.ThreadMode;
import com.yundong.milk.view.IOrderCommentView;
import com.yundong.milk.widget.XRatingBar;

import java.util.ArrayList;

/**
 * Created by lx on 2017/2/15.
 */

public class ActivityComment extends BaseActivity implements IOrderCommentView {
    private XRatingBar xrb_comment;
    private ImageView iv_add_comment_image;
    private ImageView iv_comment_goods_image;
    private RecyclerView rv_photo_list;
    private AdapterCommentPhoto adapterCommentPhoto = new AdapterCommentPhoto(this);
    private Button btn_publish;
    private EditText et_content;

    private ActivityCommentPresenter activityCommentPresenter;

    private OrderListBean.OrderListData.OrderListDataArray orderListDataArray;

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
        Glide.with(this).load(orderListDataArray.getGoods_main_image()).into(iv_comment_goods_image);
    }

    private String startCount = "3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);


        initTitle(R.string.publish_comment, true);

        iv_add_comment_image = (ImageView) findViewById(R.id.iv_add_comment_image);
        iv_comment_goods_image = (ImageView) findViewById(R.id.iv_comment_goods_image);
        iv_add_comment_image.setOnClickListener(this);
        xrb_comment = (XRatingBar) findViewById(R.id.xrb_comment);
        xrb_comment.setOnRatingChangeListener(new XRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float ratingCount) {
                ToastUtil.showShortToast(ratingCount + "颗星");
                startCount = String.valueOf(ratingCount);
            }
        });
        rv_photo_list = (RecyclerView) findViewById(R.id.rv_photo_list);
        rv_photo_list.setHasFixedSize(true);
        rv_photo_list.setLayoutManager(new GridLayoutManager(this, 4));
        rv_photo_list.setAdapter(adapterCommentPhoto);
        if (adapterCommentPhoto.getImages().size() <= 0) {
            adapterCommentPhoto.getImages().add(BitmapFactory.decodeResource(getResources(), R.mipmap.add_comment_image));
            adapterCommentPhoto.notifyDataSetChanged();
        }

        btn_publish = (Button) findViewById(R.id.btn_publish);
        btn_publish.setOnClickListener(this);

        et_content = (EditText) findViewById(R.id.et_content);

        activityCommentPresenter = ActivityCommentPresenter.getInstance().with(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //接收相册的回调
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            Bitmap image = Base64Utils.fileToBitmap(c.getString(columnIndex));
            showImage(image);
            c.close();
        }

        //接受相机的回调
        if (resultCode == RESULT_OK) {
            if (requestCode == 2) {//对应第一种方法
                /**
                 * 通过这种方法取出的拍摄会默认压缩，因为如果相机的像素比较高拍摄出来的图会比较高清，
                 * 如果图太大会造成内存溢出（OOM），因此此种方法会默认给图片尽心压缩
                 */
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                showImage(bitmap);

            }
        }
        adapterCommentPhoto.notifyDataSetChanged();
        ToastUtil.showShortToast("共有：" + adapterCommentPhoto.getImages().size() + "张图片");
    }


    private void showImage(Bitmap bitmap) {
        if (adapterCommentPhoto.getPosition() != adapterCommentPhoto.getImages().size() - 1) {
            //如果点击的不是+
            if (adapterCommentPhoto.getImages().size() > 0) {
                adapterCommentPhoto.getImages().remove(adapterCommentPhoto.getPosition());
                adapterCommentPhoto.getImages().add(adapterCommentPhoto.getPosition(), bitmap);
            } else {
                adapterCommentPhoto.getImages().add(bitmap);
            }
        } else {
            adapterCommentPhoto.getImages().remove(adapterCommentPhoto.getImages().size() - 1);
            adapterCommentPhoto.getImages().add(bitmap);
            if (adapterCommentPhoto.getImages().size() < 4) {
                adapterCommentPhoto.addData(BitmapFactory.decodeResource(getResources(), R.mipmap.add_comment_image));
            }

        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_publish:
                ArrayList<String> base64strs = new ArrayList<>();
                CommentImageBean commentImageBean = new CommentImageBean();
                for (Bitmap bitmap : adapterCommentPhoto.getImages()) {
                    String base64str = Base64Utils.bitmapToBase64(bitmap);
                    base64strs.add(base64str);
                }
                commentImageBean.setImages(base64strs);
                String json = new Gson().toJson(commentImageBean);
                Log.i("LMW", json);
                activityCommentPresenter.orderComment(YunDongApplication.getLoginBean().getData().getUserinfo().getId(), orderListDataArray.getOrder_id(), et_content.getText() + "", json, startCount);

                break;
        }
    }

    @Override
    public void orderComment(BaseReceiveBean baseReceiveBean) {
        ToastUtil.showShortToast(baseReceiveBean.getMsg());
    }

    @Override
    public void orderCommentOnError(String e) {
        ToastUtil.showShortToast(e);
    }
}
