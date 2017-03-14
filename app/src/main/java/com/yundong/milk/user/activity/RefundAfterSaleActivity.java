package com.yundong.milk.user.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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
import com.yundong.milk.user.adapter.RefundAfterSaleAdapter;

import java.util.ArrayList;

/**
 * Created by lj on 2017/1/4.
 * 退款售后
 */
public class RefundAfterSaleActivity extends BaseActivity{

    private TextView mTxtRefundReason;
    private ImageView mImgOne, mImgTwo, mImgThree;

    private ArrayList<String> mSelectPath;
    private long timeStamp = 0;
    private String lastPicUrl;
    private String picturePath_All;
    private String picturePath_rootDirectory;
    private static final int REQUEST_IMAGE_ONE = 1, REQUEST_IMAGE_TWO = 2, REQUEST_IMAGE_THREE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_after_sale);
        initTitle(R.string.commitApply, true);
        mTxtRefundReason = (TextView) findViewById(R.id.txtRefundReason);
        mImgOne = (ImageView) findViewById(R.id.imgOne);
        mImgTwo = (ImageView) findViewById(R.id.imgTwo);
        mImgThree = (ImageView) findViewById(R.id.imgThree);
        mTxtRefundReason.setOnClickListener(this);
        mImgOne.setOnClickListener(this);
        mImgTwo.setOnClickListener(this);
        mImgThree.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.txtRefundReason:
                final Dialog dialog = new Dialog(this);
                View dialogView = View.inflate(this, R.layout.dialog_refund_reason, null);
                ListView refundListView = (ListView) dialogView.findViewById(R.id.rv_platform_pca);
                final RefundAfterSaleAdapter adapter = new RefundAfterSaleAdapter(this);
                refundListView.setAdapter(adapter);
                refundListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        dialog.dismiss();
                        adapter.setSelection(i);
                        adapter.notifyDataSetChanged();
                    }
                });
                dialog.setTitle(R.string.please_select_refund_reason);
                dialog.setContentView(dialogView);
                dialog.show();
                break;
            case R.id.imgOne:
                ChooseImage.jumpSelectImage(this, REQUEST_IMAGE_ONE);
                break;
            case R.id.imgTwo:
                ChooseImage.jumpSelectImage(this, REQUEST_IMAGE_TWO);
                break;
            case R.id.imgThree:
                ChooseImage.jumpSelectImage(this, REQUEST_IMAGE_THREE);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_ONE) {
            if (resultCode == RESULT_OK) {
                mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                String url = mSelectPath.get(0);
                picturePath_All = url;
                Bitmap loacalBitmap = ChooseImage.getSmallBitmap(url);
                mImgOne.setImageBitmap(loacalBitmap);
                mImgOne.setBackground(null);
                String spStr[] = mSelectPath.get(0).split("/");
                picturePath_rootDirectory = spStr[spStr.length - 1];
                timeStamp = System.currentTimeMillis();
//                lastPicUrl = ConstantHelp.otherForwardUrl + SharedPreferenceTool.getUserId(ReleaseCoolShow.this) + "/" + timeStamp + picturePath_rootDirectory;
//                upload();
            } else {
                if (HDApp.getInstance().getSingleChooseFile() != null && HDApp.getInstance().getSingleChooseFile().getTotalSpace() > 0) {
                    String path = HDApp.getInstance().getSingleChooseFile().getAbsolutePath();
                    picturePath_All = path;
                    String spStr[] = path.split("/");
                    picturePath_rootDirectory = spStr[spStr.length - 1];
                    timeStamp = System.currentTimeMillis();
                    Bitmap loacalBitmap = ChooseImage.getSmallBitmap(path);
                    mImgOne.setImageBitmap(loacalBitmap);
                    mImgOne.setBackground(null);
//                    lastPicUrl = ConstantHelp.otherForwardUrl + SharedPreferenceTool.getUserId(ReleaseCoolShow.this) + "/" + timeStamp + picturePath_rootDirectory;
//                    upload();
                }
            }
        }else if (requestCode == REQUEST_IMAGE_TWO) {
            if (resultCode == RESULT_OK) {
                mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                String url = mSelectPath.get(0);
                picturePath_All = url;
                Bitmap loacalBitmap = ChooseImage.getSmallBitmap(url);
                mImgTwo.setImageBitmap(loacalBitmap);
                mImgTwo.setBackground(null);
                String spStr[] = mSelectPath.get(0).split("/");
                picturePath_rootDirectory = spStr[spStr.length - 1];
                timeStamp = System.currentTimeMillis();
//                lastPicUrl = ConstantHelp.otherForwardUrl + SharedPreferenceTool.getUserId(ReleaseCoolShow.this) + "/" + timeStamp + picturePath_rootDirectory;
//                upload();
            } else {
                if (HDApp.getInstance().getSingleChooseFile() != null && HDApp.getInstance().getSingleChooseFile().getTotalSpace() > 0) {
                    String path = HDApp.getInstance().getSingleChooseFile().getAbsolutePath();
                    picturePath_All = path;
                    String spStr[] = path.split("/");
                    picturePath_rootDirectory = spStr[spStr.length - 1];
                    timeStamp = System.currentTimeMillis();
                    Bitmap loacalBitmap = ChooseImage.getSmallBitmap(path);
                    mImgTwo.setImageBitmap(loacalBitmap);
                    mImgTwo.setBackground(null);
//                    lastPicUrl = ConstantHelp.otherForwardUrl + SharedPreferenceTool.getUserId(ReleaseCoolShow.this) + "/" + timeStamp + picturePath_rootDirectory;
//                    upload();
                }
            }
        }else if (requestCode == REQUEST_IMAGE_THREE) {
            if (resultCode == RESULT_OK) {
                mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                String url = mSelectPath.get(0);
                picturePath_All = url;
                Bitmap loacalBitmap = ChooseImage.getSmallBitmap(url);
                mImgThree.setImageBitmap(loacalBitmap);
                mImgThree.setBackground(null);
                String spStr[] = mSelectPath.get(0).split("/");
                picturePath_rootDirectory = spStr[spStr.length - 1];
                timeStamp = System.currentTimeMillis();
//                lastPicUrl = ConstantHelp.otherForwardUrl + SharedPreferenceTool.getUserId(ReleaseCoolShow.this) + "/" + timeStamp + picturePath_rootDirectory;
//                upload();
            } else {
                if (HDApp.getInstance().getSingleChooseFile() != null && HDApp.getInstance().getSingleChooseFile().getTotalSpace() > 0) {
                    String path = HDApp.getInstance().getSingleChooseFile().getAbsolutePath();
                    picturePath_All = path;
                    String spStr[] = path.split("/");
                    picturePath_rootDirectory = spStr[spStr.length - 1];
                    timeStamp = System.currentTimeMillis();
                    Bitmap loacalBitmap = ChooseImage.getSmallBitmap(path);
                    mImgThree.setImageBitmap(loacalBitmap);
                    mImgThree.setBackground(null);
//                    lastPicUrl = ConstantHelp.otherForwardUrl + SharedPreferenceTool.getUserId(ReleaseCoolShow.this) + "/" + timeStamp + picturePath_rootDirectory;
//                    upload();
                }
            }
        }
    }
}
