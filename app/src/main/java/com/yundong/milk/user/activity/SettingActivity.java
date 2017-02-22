package com.yundong.milk.user.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.imagechoose.ChooseImage;
import com.yundong.milk.imagechoose.MultiImageSelectorActivity;
import com.yundong.milk.imagechoose.crop.HDApp;
import com.yundong.milk.view.dialog.SweetAlertDialog;

import java.util.ArrayList;

/**
 * Created by lj on 2016/12/30.
 * 设置
 */
public class SettingActivity extends BaseActivity{

    private ImageView mImgUserHead;
    private ArrayList<String> mSelectPath;
    private long timeStamp = 0;
    private String picturePath_All;
    private String picturePath_rootDirectory;
    private static final int REQUEST_IMAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initTitle(R.string.setting, true, getString(R.string.save), this);
        findViewById(R.id.reHead).setOnClickListener(this);
        findViewById(R.id.reNickName).setOnClickListener(this);
        findViewById(R.id.txtLoginOut).setOnClickListener(this);
        mImgUserHead = (ImageView) findViewById(R.id.imgUserHead);
        mImgUserHead.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.imgUserHead://我的头像
                ChooseImage.jumpSelectImage(this, REQUEST_IMAGE);
                break;
            case R.id.reNickName://我的昵称
                startActivity(new Intent(this, MineNickNameActivity.class));
                break;
            case R.id.txtRight://保存
                break;
            case R.id.txtLoginOut://退出登录
                final SweetAlertDialog dialog = new SweetAlertDialog(this);
                dialog.showCancelButton(true);
                dialog.setTitleText(getString(R.string.whether_login_out));
                dialog.setCancelText(getString(R.string.cancel));
                dialog.setConfirmText(getString(R.string.confirm));
                dialog.show();
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        dialog.dismiss();
                        finish();
                    }
                });
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
                mImgUserHead.setImageBitmap(loacalBitmap);
                mImgUserHead.setBackground(null);
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
                    mImgUserHead.setImageBitmap(loacalBitmap);
                    mImgUserHead.setBackground(null);
                }
            }
        }
    }
}
