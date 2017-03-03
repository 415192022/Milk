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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.user.adapter.AdapterCommentPhoto;
import com.yundong.milk.util.Base64Utils;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.widget.XRatingBar;

/**
 * Created by lx on 2017/2/15.
 */

public class ActivityComment extends BaseActivity {
    private XRatingBar xrb_comment;
    private ImageView iv_add_comment_image;
    private RecyclerView rv_photo_list;
    private AdapterCommentPhoto adapterCommentPhoto = new AdapterCommentPhoto(this);
    private Button btn_publish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);


        initTitle(R.string.publish_comment,true);

        iv_add_comment_image = (ImageView) findViewById(R.id.iv_add_comment_image);
        iv_add_comment_image.setOnClickListener(this);
        xrb_comment = (XRatingBar) findViewById(R.id.xrb_comment);
        xrb_comment.setOnRatingChangeListener(new XRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float ratingCount) {
                ToastUtil.showShortToast(ratingCount + "颗星");
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

        btn_publish= (Button) findViewById(R.id.btn_publish);
        btn_publish.setOnClickListener(this);
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
        ToastUtil.showShortToast("共有："+adapterCommentPhoto.getImages().size()+"张图片");
    }


    private void showImage(Bitmap bitmap){
        if(adapterCommentPhoto.getPosition() != adapterCommentPhoto.getImages().size()-1){
            //如果点击的不是+
            if(adapterCommentPhoto.getImages().size() >0){
                adapterCommentPhoto.getImages().remove(adapterCommentPhoto.getPosition());
                adapterCommentPhoto.getImages().add(adapterCommentPhoto.getPosition(),bitmap);
            }else{
                adapterCommentPhoto.getImages().add(bitmap);
            }
        }else{
            adapterCommentPhoto.getImages().remove(adapterCommentPhoto.getImages().size()-1);
            adapterCommentPhoto.getImages().add(bitmap);
            if(adapterCommentPhoto.getImages().size()<4){
                adapterCommentPhoto.addData(BitmapFactory.decodeResource(getResources(), R.mipmap.add_comment_image));
            }

        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_publish:

                break;
        }
    }

}
