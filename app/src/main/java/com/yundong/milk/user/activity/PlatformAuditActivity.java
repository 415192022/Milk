package com.yundong.milk.user.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.imagechoose.ChooseImage;
import com.yundong.milk.imagechoose.MultiImageSelectorActivity;
import com.yundong.milk.imagechoose.crop.HDApp;
import com.yundong.milk.user.adapter.PlatformAreaAdapter;

import java.util.ArrayList;

/**
 * Created by lj on 2017/1/19.
 * 平台审核
 */
public class PlatformAuditActivity extends BaseActivity{

    private TextView mTxtCompanyArea;
    private ImageView mImgLicense;
    private static final int REQUEST_IMAGE = 2;
    private ArrayList<String> mSelectPath;
    private long timeStamp = 0;
    private String picturePath_All;
    private String picturePath_rootDirectory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platform_audit);
        initTitle(R.string.fill_in_the_audit_information, true);
        mImgLicense = (ImageView) findViewById(R.id.imgLicense);
        mImgLicense.setOnClickListener(this);
        findViewById(R.id.txtComplete).setOnClickListener(this);
        mTxtCompanyArea = (TextView) findViewById(R.id.txtCompanyArea);
        mTxtCompanyArea.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.txtCompanyArea:
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
            case R.id.imgLicense://上传营业执照
                ChooseImage.jumpSelectImage(this, REQUEST_IMAGE);
                break;
            case R.id.txtComplete: //完成
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
                mImgLicense.setImageBitmap(loacalBitmap);
                mImgLicense.setBackground(null);
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
                    mImgLicense.setImageBitmap(loacalBitmap);
                    mImgLicense.setBackground(null);
                }
            }
        }
    }
}
