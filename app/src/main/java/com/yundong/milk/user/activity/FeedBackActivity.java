package com.yundong.milk.user.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.cache.CacheActivity;
import com.yundong.milk.imagechoose.ChooseImage;
import com.yundong.milk.imagechoose.MultiImageSelectorActivity;
import com.yundong.milk.imagechoose.crop.HDApp;
import com.yundong.milk.manager.YunDongApplication;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.send.CommentImageBean;
import com.yundong.milk.present.FeedBackActivityPresenter;
import com.yundong.milk.util.Base64Utils;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.view.IFeedBackView;

import java.util.ArrayList;

/**
 * Created by lj on 2016/11/20.
 * 意见反馈
 */
public class FeedBackActivity extends BaseActivity implements View.OnClickListener, IFeedBackView {

    private ImageView mImgAddPic;
    private ArrayList<String> mSelectPath;
    private long timeStamp = 0;
    private String picturePath_All;
    private String picturePath_rootDirectory;
    private static final int REQUEST_IMAGE = 2;

    private FeedBackActivityPresenter feedBackActivityPresenter;
    private Button btnCommitFeedBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        initTitle(R.string.feed_back, true);
        btnCommitFeedBack = (Button) findViewById(R.id.btnCommitFeedBack);
        btnCommitFeedBack.setOnClickListener(this);
        mImgAddPic = (ImageView) findViewById(R.id.imgAddPic);
        mImgAddPic.setOnClickListener(this);
        if (!CacheActivity.activityList.contains(FeedBackActivity.this)) {
            CacheActivity.addActivity(FeedBackActivity.this);
        }
        feedBackActivityPresenter = FeedBackActivityPresenter.getInstance().with(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCommitFeedBack:
                btnCommitFeedBack.setClickable(false);
                String content = ((EditText) findViewById(R.id.editContent)).getText().toString().trim();
                String phoneNum = ((EditText) findViewById(R.id.editPhoneNum)).getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    ToastUtil.showShortToast(getString(R.string.please_input_feed_back_content));
                    btnCommitFeedBack.setClickable(true);
                } else if (TextUtils.isEmpty(phoneNum)) {
                    ToastUtil.showShortToast(getString(R.string.please_input_phone_number));
                    btnCommitFeedBack.setClickable(true);
                } else if (phoneNum.length() != 11) {
                    ToastUtil.showShortToast(getString(R.string.phone_number_not_correct));
                    btnCommitFeedBack.setClickable(true);
                } else {
                    findViewById(R.id.btnCommitFeedBack).setClickable(false);
                    if (images.size() > 0) {
                        CommentImageBean commentImageBean = new CommentImageBean();
                        ArrayList<String> ig = new ArrayList<String>();
                        ig.add(Base64Utils.bitmapToBase64(images.get(0)));
                        commentImageBean.setImages(ig);
                        String json = new Gson().toJson(commentImageBean);
                        feedBackActivityPresenter.feedBack(YunDongApplication.getLoginBean().getData().getUserinfo().getId(), content, phoneNum, json)
                        ;

                    } else {
                        feedBackActivityPresenter.feedBack(YunDongApplication.getLoginBean().getData().getUserinfo().getId(), content, phoneNum, "");
                    }
                }
                break;
            case R.id.imgAddPic:
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
                mImgAddPic.setImageBitmap(loacalBitmap);
                images.clear();
                images.add(loacalBitmap);
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
                    images.clear();
                    images.add(loacalBitmap);
                    mImgAddPic.setBackground(null);
                }
            }
        }
    }

    @Override
    public void feedBack(BaseReceiveBean baseReceiveBean) {
        ToastUtil.showShortToast(baseReceiveBean.getMsg());
        finish();
        btnCommitFeedBack.setClickable(true);
    }

    @Override
    public void feedBackOnError(String e) {
        ToastUtil.showShortToast("提交反馈出错" + e);
        btnCommitFeedBack.setClickable(false);
    }
}
