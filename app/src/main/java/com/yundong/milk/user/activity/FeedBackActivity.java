package com.yundong.milk.user.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.cache.CacheActivity;
import com.yundong.milk.imagechoose.ChooseImage;
import com.yundong.milk.imagechoose.MultiImageSelectorActivity;
import com.yundong.milk.imagechoose.crop.HDApp;
import com.yundong.milk.util.ToastUtil;

import java.util.ArrayList;

/**
 * Created by lj on 2016/11/20.
 * 意见反馈
 */
public class FeedBackActivity extends BaseActivity implements View.OnClickListener{

    private ImageView mImgAddPic;
    private ArrayList<String> mSelectPath;
    private long timeStamp = 0;
    private String picturePath_All;
    private String picturePath_rootDirectory;
    private static final int REQUEST_IMAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        initTitle(R.string.feed_back, true);
        findViewById(R.id.btnCommitFeedBack).setOnClickListener(this);
        mImgAddPic = (ImageView) findViewById(R.id.imgAddPic);
        mImgAddPic.setOnClickListener(this);
        if (!CacheActivity.activityList.contains(FeedBackActivity.this)){
            CacheActivity.addActivity(FeedBackActivity.this);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCommitFeedBack:
                String content = ((EditText) findViewById(R.id.editContent)).getText().toString().trim();
                String phoneNum = ((EditText) findViewById(R.id.editPhoneNum)).getText().toString().trim();
                if (TextUtils.isEmpty(content)){
                    ToastUtil.showShortToast(getString(R.string.please_input_feed_back_content));
                }else if (TextUtils.isEmpty(phoneNum)){
                    ToastUtil.showShortToast(getString(R.string.please_input_phone_number));
                }else if (phoneNum.length() != 11){
                    ToastUtil.showShortToast(getString(R.string.phone_number_not_correct));
                }else {
                    startActivity(new Intent(this, FeedBackComActivity.class));
                    finish();
                }
                break;
            case R.id.imgAddPic:
                ChooseImage.jumpSelectImage(this, REQUEST_IMAGE);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                String url = mSelectPath.get(0);
                picturePath_All = url;
                Bitmap loacalBitmap = ChooseImage.getSmallBitmap(url);
                mImgAddPic.setImageBitmap(loacalBitmap);
                mImgAddPic.setBackground(null);
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
                    mImgAddPic.setImageBitmap(loacalBitmap);
                    mImgAddPic.setBackground(null);
                }
            }
        }
    }
}
