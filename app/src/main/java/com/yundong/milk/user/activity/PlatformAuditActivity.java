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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
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
import com.yundong.milk.adapter.PCAAdapter;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.imagechoose.ChooseImage;
import com.yundong.milk.imagechoose.MultiImageSelectorActivity;
import com.yundong.milk.imagechoose.crop.HDApp;
import com.yundong.milk.manager.YunDongApplication;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.PCABean;
import com.yundong.milk.model.PlatformAuditBean;
import com.yundong.milk.present.PerfectAddressActivityPresenter;
import com.yundong.milk.present.PlatformAuditActivityPresenter;
import com.yundong.milk.user.adapter.PlatformAreaAdapter;
import com.yundong.milk.util.Base64Utils;
import com.yundong.milk.util.BitmapUtils;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.view.IApplyForChangeCompanyView;
import com.yundong.milk.view.ICommitPlateformAuditView;
import com.yundong.milk.view.IPCAView;
import com.yundong.milk.view.IPlateformAuditView;
import com.yundong.milk.widget.popupwindow.PCAPopupWindow;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lj on 2017/1/19.
 * 平台审核
 */
public class PlatformAuditActivity extends BaseActivity implements
        IPlateformAuditView
        , ICommitPlateformAuditView
        , IPCAView
        , PCAPopupWindow.OnCompleteListenner
        , IApplyForChangeCompanyView {

    private static final int REQUEST_IMAGE = 2;
    private ArrayList<String> mSelectPath;
    private long timeStamp = 0;
    private String picturePath_All;
    private String picturePath_rootDirectory;


    private EditText et_CompanyName;
    private ImageView iv_License;
    private TextView tv_CompanyArea;
    private TextView tv_Complete;
    private EditText et_ChargeMan;
    private EditText et_Phone;

    private PlatformAuditActivityPresenter platformAuditActivityPresenter;

    private PerfectAddressActivityPresenter perfectAddressActivityPresenter;
    PCAAdapter pAdapter;
    PCAAdapter cAdapter;
    PCAAdapter aAdapter;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platform_audit);
        initTitle(R.string.fill_in_the_audit_information, true);
        iv_License = (ImageView) findViewById(R.id.iv_License);
        iv_License.setOnClickListener(this);
        tv_Complete= (TextView) findViewById(R.id.tv_Complete);
        tv_Complete.setClickable(false);
        tv_Complete.setOnClickListener(this);
        tv_CompanyArea = (TextView) findViewById(R.id.tv_CompanyArea);
        tv_CompanyArea.setOnClickListener(this);

        et_CompanyName = (EditText) findViewById(R.id.et_CompanyName);
        et_ChargeMan = (EditText) findViewById(R.id.et_ChargeMan);
        et_Phone = (EditText) findViewById(R.id.et_Phone);
        dialogView = View.inflate(this, R.layout.dialog_refund_reason, null);
        rv_platform_pca = (RecyclerView) dialogView.findViewById(R.id.rv_platform_pca);
        rv_platform_pca.setHasFixedSize(true);
        rv_platform_pca.setLayoutManager(new LinearLayoutManager(PlatformAuditActivity.this));
        pAdapter = new PCAAdapter(PlatformAuditActivity.this);
        cAdapter = new PCAAdapter(PlatformAuditActivity.this);
        aAdapter = new PCAAdapter(PlatformAuditActivity.this);

        dialog = new Dialog(this);
        dialog.setTitle(R.string.please_select_refund_reason);
        dialog.setContentView(dialogView);

        platformAuditActivityPresenter = PlatformAuditActivityPresenter.getinstance().with(this, this, this);
        platformAuditActivityPresenter.platformAudit(YunDongApplication.getLoginBean().getData().getUserinfo().getId());


        perfectAddressActivityPresenter = PerfectAddressActivityPresenter.getInstance().with(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_CompanyArea:
                //获得省市区
//                dialog.show();
//                perfectAddressActivityPresenter.getP("");
                PCAPopupWindow pcaPopupWindow = new PCAPopupWindow(this, this);
                pcaPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.iv_License://上传营业执照
                ChooseImage.jumpSelectImage(this, REQUEST_IMAGE);
                break;
            case R.id.tv_Complete:

                if (null != platformAuditBean) {
                    if (null == platformAuditBean.getData()) {
//完成
                        String companyName = et_CompanyName.getText() + "".trim();
                        String array = tv_CompanyArea.getText() + "".trim();
                        String chargeMan = et_ChargeMan.getText() + "".trim();
                        String phone = et_Phone.getText() + "".trim();

                        if (null == companyName || "".equals(companyName)) {
                            ToastUtil.showShortToast("公司名称不能为空");
                            return;
                        }
                        if (null == province) {
                            ToastUtil.showShortToast("请选择省");
                            return;
                        }
                        if (null == city) {
                            ToastUtil.showShortToast("请选择市");
                            return;
                        }
                        if (null == area) {
                            ToastUtil.showShortToast("请选择区");
                            return;
                        }
                        if (null == chargeMan || "".equals(chargeMan)) {
                            ToastUtil.showShortToast("负责人不能为空");
                            return;
                        }
                        if (null == phone || "".equals(phone)) {
                            ToastUtil.showShortToast("联系方式不能为空");
                            return;
                        }
                        if (null == bitmap) {
                            ToastUtil.showShortToast("营业执照不能为空");
                            return;
                        } else {
                            platformAuditActivityPresenter.commitPlateformAudit(
                                    YunDongApplication.getLoginBean().getData().getUserinfo().getId(),
                                    companyName,
                                    chargeMan,
                                    phone,
                                    Base64Utils.bitmapToBase64(bitmap),
                                    province.getArea_name(),
                                    province.getArea_id(),
                                    city.getArea_name(),
                                    city.getArea_id(),
                                    area.getArea_name(),
                                    area.getArea_id(),
                                    ""
                            );
                        }
                    }else {
                        //修改
                        platformAuditActivityPresenter.applyForCompany(platformAuditBean.getData().getCompany_id());
                    }
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

    private PlatformAuditBean platformAuditBean;

    //获取审核资料
    @Override
    public void plateformAudit(PlatformAuditBean platformAuditBean) {
        if (null != platformAuditBean.getData()) {
            ((TextView) findViewById(R.id.tv_Complete)).setText("申请修改");
        } else {
            ((TextView) findViewById(R.id.tv_Complete)).setText("完 成");
        }
        this.platformAuditBean = platformAuditBean;
        et_CompanyName.setText(platformAuditBean.getData().getCompany_name());
        Glide.with(this).load(platformAuditBean.getData().getLicense()).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {

                bitmap = BitmapUtils.drawableToBitamp(resource);
                iv_License.setImageBitmap(bitmap);
            }
        });
        tv_CompanyArea.setText(platformAuditBean.getData().getCompany_name());
        et_ChargeMan.setText(platformAuditBean.getData().getCharge_people());
        et_Phone.setText(platformAuditBean.getData().getCharge_phone());
        ((TextView) findViewById(R.id.tv_CompanyArea)).setText(platformAuditBean.getData().getProvince_name() + " " + platformAuditBean.getData().getCity_name() + " " + platformAuditBean.getData().getArea_name());
        tv_Complete.setClickable(true);
    }

    @Override
    public void plateformAuditOnError(String e) {
        ToastUtil.showShortToast(e);
    }

    //提交审核资料
    @Override
    public void commitPlateformAudit(BaseReceiveBean baseReceiveBean) {
        ToastUtil.showShortToast(baseReceiveBean.getMsg());
        finish();
    }

    @Override
    public void commitPlateformAuditOnError(String e) {
        ToastUtil.showShortToast(e);
    }

    View dialogView;
    RecyclerView rv_platform_pca;
    private PCABean.PCAData province;
    private PCABean.PCAData city;
    private PCABean.PCAData area;

    //获得省
    @Override
    public void getP(final PCABean pcaBean) {

        Observable.from(pcaBean.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PCABean.PCAData>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        pAdapter.getPcaDatas().clear();
                        pAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCompleted() {
                        rv_platform_pca.setAdapter(pAdapter);
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
        pAdapter.setOnItemClickListner(new PCAAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(View view, int position) {
                perfectAddressActivityPresenter.getC(pAdapter.getPcaDatas().get(position).getArea_id());
                province = pAdapter.getPcaDatas().get(position);
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
                        cAdapter.notifyDataSetChanged();
                        dialog.setTitle("选择市");
                    }

                    @Override
                    public void onCompleted() {
                        rv_platform_pca.setAdapter(cAdapter);
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
        cAdapter.setOnItemClickListner(new PCAAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(View view, int position) {
                city = cAdapter.getPcaDatas().get(position);
                perfectAddressActivityPresenter.getA(cAdapter.getPcaDatas().get(position).getArea_id());
            }
        });

    }

    @Override
    public void getCOnError(String e) {

    }

    //获得区
    @Override
    public void getA(PCABean pcaBean) {
        aAdapter.setOnItemClickListner(new PCAAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(View view, int position) {
                area = aAdapter.getPcaDatas().get(position);
                dialog.dismiss();
                tv_CompanyArea.setText(province.getArea_name() + " " + city.getArea_name() + " " + area.getArea_name());
            }
        });
        Observable.from(pcaBean.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PCABean.PCAData>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        aAdapter.getPcaDatas().clear();
                        aAdapter.notifyDataSetChanged();
                        dialog.setTitle("选择区/县");

                    }

                    @Override
                    public void onCompleted() {
                        rv_platform_pca.setAdapter(aAdapter);
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

    @Override
    public void OnComplete(PCABean.PCAData province, PCABean.PCAData city, PCABean.PCAData area) {
        this.province = province;
        this.city = city;
        this.area = area;
        ((TextView) findViewById(R.id.tv_CompanyArea)).setText(province.getArea_name() + " " + city.getArea_name() + " " + area.getArea_name());
    }

    @Override
    public void applyForChangeCompany(BaseReceiveBean baseReceiveBean) {
        ToastUtil.showShortToast(baseReceiveBean.getMsg());
        finish();
    }

    @Override
    public void applyForChangeCompanyOnError(String e) {

    }
}
