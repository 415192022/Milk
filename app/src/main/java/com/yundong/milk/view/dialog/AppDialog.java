package com.yundong.milk.view.dialog;

import android.content.Context;

/**
 * Created by lj on 2016/9/8.
 */
public class AppDialog {

    private static SweetAlertDialog mDialog;

    //显示loading对话框 且不可取消
    public static void showLoadingDialog(Context context, String loadingInfo) {
        if (mDialog != null) {
            mDialog.cancel();
        }
        mDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        mDialog.setTitleText(loadingInfo);
        mDialog.show();
        mDialog.setCanceledOnTouchOutside(false);
    }

    //显示消息对话框  显示标题和内容
    public static void showMsgDialog(Context context, String title, String content, SweetAlertDialog.OnSweetClickListener listener) {
        if (mDialog != null) {
            mDialog.cancel();
        }
        mDialog = new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE);
        if (title == null) {
            mDialog.setTitleText(content);
        } else {
            mDialog.setTitleText(title);
            mDialog.setContentText(content);
        }
        mDialog.setConfirmText("确定");
        if(null != listener) {
            mDialog.setConfirmClickListener(listener);
        } else {
            mDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.cancel();
                }
            });
        }
        mDialog.show();
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);
    }

    //显示消息对话框 只有内容 没有title
    public static void showMsgDialog(Context context, String content) {
        showMsgDialog(context, null, content, null);
    }

    public static void showAskDialog(Context context, String title, SweetAlertDialog.OnSweetClickListener listener) {
        if (mDialog != null) {
            mDialog.cancel();
        }
        new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE).setTitleText(title)
                .setCancelText("取消")
                .setConfirmText("确定")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.cancel();
                    }
                })
                .setConfirmClickListener(listener)
                .show();
    }

    public static void hideDialog() {
        if (mDialog != null) {
            mDialog.cancel();
        }
    }

}
