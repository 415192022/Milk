package com.yundong.milk.user.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.imagechoose.ChooseImage;
import com.yundong.milk.imagechoose.MultiImageSelectorActivity;
import com.yundong.milk.imagechoose.crop.HDApp;
import com.yundong.milk.main.activity.LoginActivity;
import com.yundong.milk.main.activity.MainActivity;
import com.yundong.milk.manager.YunDongApplication;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.LoginBean;
import com.yundong.milk.present.LoginPresenter;
import com.yundong.milk.present.SetingActivityPresenter;
import com.yundong.milk.util.Base64Utils;
import com.yundong.milk.util.Const;
import com.yundong.milk.util.PreferencesUtils;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.util.rxbus.RxBus;
import com.yundong.milk.view.ILoginView;
import com.yundong.milk.view.IModifyNickNameView;
import com.yundong.milk.view.IUploadHeadView;
import com.yundong.milk.widget.CircleImageView;
import com.yundong.milk.widget.dialog.SweetAlertDialog;

import java.util.ArrayList;

/**
 * Created by lj on 2016/12/30.
 * 设置
 */
public class SettingActivity extends BaseActivity
        implements IUploadHeadView, IModifyNickNameView, ILoginView {

    private ImageView mImgUserHead;
    private TextView tv_nick_name;
    private CircleImageView imgUserHead;
    private ArrayList<String> mSelectPath;
    private long timeStamp = 0;
    private String picturePath_All;
    private String picturePath_rootDirectory;
    private static final int REQUEST_IMAGE = 2;

    private SetingActivityPresenter setingActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initTitle(R.string.setting, true, getString(R.string.save), this);
        findViewById(R.id.reHead).setOnClickListener(this);
        findViewById(R.id.reNickName).setOnClickListener(this);
        findViewById(R.id.tv_modify).setOnClickListener(this);
        tv_nick_name = (TextView) findViewById(R.id.tv_nick_name);
        mImgUserHead = (ImageView) findViewById(R.id.imgUserHead);
        Glide.with(this).load(YunDongApplication.getLoginBean().getData().getUserinfo().getAvatar()).into(mImgUserHead);
        mImgUserHead.setOnClickListener(this);
        tv_nick_name.setText(YunDongApplication.getLoginBean().getData().getUserinfo().getUname());
        setingActivityPresenter = SetingActivityPresenter.getInstance().with(this, this);
        LoginPresenter.getInstance().with(this).login(YunDongApplication.getLoginBean().getData().getUserinfo().getPhone(), PreferencesUtils.getString(this, Const.LOGIN_PWD));
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.imgUserHead://我的头像
                ChooseImage.jumpSelectImage(this, REQUEST_IMAGE);
                break;
            case R.id.reNickName://我的昵称
                startActivityForResult(new Intent(this, MineNickNameActivity.class), 100);
                break;
            case R.id.txtRight://保存
                if ("".equals(imageBase64) || "".equals(name)) {
                    ToastUtil.showShortToast("用户头像或昵称为空");
                } else {
                    setingActivityPresenter.upLoadHead(imageBase64, YunDongApplication.getLoginBean().getData().getUserinfo().getId());
                    setingActivityPresenter.modifyNickName(name, YunDongApplication.getLoginBean().getData().getUserinfo().getId());
                }
                break;
            case R.id.tv_modify://退出登录
                final SweetAlertDialog dialog = new SweetAlertDialog(this);
                dialog.showCancelButton(true);
                dialog.setTitleText("您确定要注销账户吗？");
                dialog.setCancelText(getString(R.string.cancel));
                dialog.setConfirmText(getString(R.string.confirm));
                dialog.show();
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        dialog.dismiss();
                        //注销账户
                        finish();
                        YunDongApplication.getApplication().finishActivity(MainActivity.class);
                        Intent intent = new Intent();
                        intent.putExtra("IS_AUTO_LOGIN",false);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//它可以关掉所要到的界面中间的activity
                        intent.setClass(SettingActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
                break;
        }
    }

    String imageBase64;
    String name;

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
                mImgUserHead.setImageBitmap(loacalBitmap);
                mImgUserHead.setBackground(null);
                String spStr[] = mSelectPath.get(0).split("/");
                picturePath_rootDirectory = spStr[spStr.length - 1];
                timeStamp = System.currentTimeMillis();
                imageBase64 = Base64Utils.bitmapToBase64(loacalBitmap);

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
                    imageBase64 = Base64Utils.bitmapToBase64(loacalBitmap);
                }
            }
        } else if (requestCode == 100) {
            if (null != data && null != data.getStringExtra("NICK_NAME")) {
                name = data.getStringExtra("NICK_NAME");
                tv_nick_name.setText(name);
            }

        }

    }

    //上传头像
    @Override
    public void upLoadHead(BaseReceiveBean baseReceiveBean) {
        ToastUtil.showShortToast(baseReceiveBean.getMsg());
    }

    @Override
    public void upLoadHeadOnError(String e) {
        ToastUtil.showShortToast("头像上传失败" + e);
    }

    //修改昵称
    @Override
    public void modifyNickName(BaseReceiveBean baseReceiveBean) {
        ToastUtil.showShortToast(baseReceiveBean.getMsg());
    }

    @Override
    public void modifyNickNameOnError(String e) {
        ToastUtil.showShortToast("修改昵称失败" + e);
    }


    @Override
    public void login(LoginBean baseReceiveBean) {
        Glide.with(this).load(baseReceiveBean.getData().getUserinfo().getAvatar()).into(mImgUserHead);
        tv_nick_name.setText(baseReceiveBean.getData().getUserinfo().getUname());
        PreferencesUtils.putString(this,Const.LOGIN_AVATAR,baseReceiveBean.getData().getUserinfo().getAvatar());
        PreferencesUtils.putString(this,Const.LOGIN_NAME,baseReceiveBean.getData().getUserinfo().getUname());
    }

    @Override
    public void loginOnError(String e) {

    }
}
