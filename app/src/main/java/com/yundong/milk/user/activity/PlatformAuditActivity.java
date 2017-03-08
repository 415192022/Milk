package com.yundong.milk.user.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.imagechoose.ChooseImage;
import com.yundong.milk.imagechoose.MultiImageSelectorActivity;
import com.yundong.milk.imagechoose.crop.HDApp;
import com.yundong.milk.manager.YunDongApplication;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.PlatformAuditBean;
import com.yundong.milk.present.PlatformAuditActivityPresenter;
import com.yundong.milk.user.adapter.PlatformAreaAdapter;
import com.yundong.milk.util.Base64Utils;
import com.yundong.milk.util.BitmapUtils;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.view.ICommitPlateformAuditView;
import com.yundong.milk.view.IPlateformAuditView;

import java.util.ArrayList;

/**
 * Created by lj on 2017/1/19.
 * 平台审核
 */
public class PlatformAuditActivity extends BaseActivity implements IPlateformAuditView, ICommitPlateformAuditView {

    private static final int REQUEST_IMAGE = 2;
    private ArrayList<String> mSelectPath;
    private long timeStamp = 0;
    private String picturePath_All;
    private String picturePath_rootDirectory;


    private EditText et_CompanyName;
    private ImageView iv_License;
    private TextView tv_CompanyArea;
    private EditText et_ChargeMan;
    private EditText et_Phone;

    private PlatformAuditActivityPresenter platformAuditActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platform_audit);
        initTitle(R.string.fill_in_the_audit_information, true);
        iv_License = (ImageView) findViewById(R.id.iv_License);
        iv_License.setOnClickListener(this);
        findViewById(R.id.tv_Complete).setOnClickListener(this);
        tv_CompanyArea = (TextView) findViewById(R.id.tv_CompanyArea);
        tv_CompanyArea.setOnClickListener(this);

        et_CompanyName = (EditText) findViewById(R.id.et_CompanyName);
        et_ChargeMan = (EditText) findViewById(R.id.et_ChargeMan);
        et_Phone = (EditText) findViewById(R.id.et_Phone);

        platformAuditActivityPresenter = PlatformAuditActivityPresenter.getinstance().with(this, this);
        platformAuditActivityPresenter.platformAudit(YunDongApplication.getLoginBean().getData().getUserinfo().getId());
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_CompanyArea:
                final Dialog dialog = new Dialog(this);
                View dialogView = View.inflate(this, R.layout.dialog_refund_reason, null);
                ListView refundListView = (ListView) dialogView.findViewById(R.id.refundListView);
                final PlatformAreaAdapter adapter = new PlatformAreaAdapter(this);
                refundListView.setAdapter(adapter);
                refundListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        dialog.dismiss();
                        adapter.setSelection(i);
                        adapter.notifyDataSetChanged();
//                        mTxtCompanyArea.setText(adapter.getItem(i).toString());
                    }
                });
                dialog.setTitle(R.string.please_select_refund_reason);
                dialog.setContentView(dialogView);
                dialog.show();
                break;
            case R.id.iv_License://上传营业执照
                ChooseImage.jumpSelectImage(this, REQUEST_IMAGE);
                break;
            case R.id.tv_Complete: //完成
                String companyName = et_CompanyName.getText() + "".trim();
                String array = tv_CompanyArea.getText() + "".trim();
                String chargeMan = et_ChargeMan.getText() + "".trim();
                String phone = et_Phone.getText() + "".trim();
                if (null == bitmap) {
                    platformAuditActivityPresenter.commitPlateformAudit(
                            YunDongApplication.getLoginBean().getData().getUserinfo().getId()
                            , companyName
                            , array
                            , "1"
                            , chargeMan
                            , phone
                            , ""
                    );
                } else {
                    platformAuditActivityPresenter.commitPlateformAudit(
                            YunDongApplication.getLoginBean().getData().getUserinfo().getId()
                            , companyName
                            , array
                            , "1"
                            , chargeMan
                            , phone
                            , Base64Utils.bitmapToBase64(bitmap)
                    );
                }

                break;
        }
    }

    private Bitmap bitmap = null;

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
                iv_License.setImageBitmap(loacalBitmap);
                bitmap = loacalBitmap;
                iv_License.setBackground(null);
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
                    iv_License.setImageBitmap(loacalBitmap);
                    bitmap = loacalBitmap;
                    iv_License.setBackground(null);
                }
            }
        }
    }

    //获取审核资料
    @Override
    public void plateformAudit(PlatformAuditBean platformAuditBean) {
        et_CompanyName.setText(platformAuditBean.getData().getCompany_name());
        Glide.with(this).load(platformAuditBean.getData().getLicense()).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {

                bitmap = BitmapUtils.drawableToBitamp(resource);
                iv_License.setImageBitmap(bitmap);
            }
        });
        tv_CompanyArea.setText(platformAuditBean.getData().getArea());
        et_ChargeMan.setText(platformAuditBean.getData().getCharge_people());
        et_Phone.setText(platformAuditBean.getData().getCharge_phone());
    }

    @Override
    public void plateformAuditOnError(String e) {
        ToastUtil.showShortToast(e);
    }

    //提交审核资料
    @Override
    public void commitPlateformAudit(BaseReceiveBean baseReceiveBean) {
        ToastUtil.showShortToast(baseReceiveBean.getMsg());
    }

    @Override
    public void commitPlateformAuditOnError(String e) {
        ToastUtil.showShortToast(e);
    }
}
