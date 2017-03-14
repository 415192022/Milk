package com.yundong.milk.user.fragment;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yundong.milk.R;
import com.yundong.milk.base.BaseFragment;
import com.yundong.milk.manager.YunDongApplication;
import com.yundong.milk.model.LoginBean;
import com.yundong.milk.present.LoginPresenter;
import com.yundong.milk.user.activity.FeedBackActivity;
import com.yundong.milk.user.activity.MessageCenterActivity;
import com.yundong.milk.user.activity.MineCollectionActivity;
import com.yundong.milk.user.activity.MineOrderActivity;
import com.yundong.milk.user.activity.OrderDetailActivity;
import com.yundong.milk.user.activity.PlatformAuditActivity;
import com.yundong.milk.user.activity.ReceiptAddressActivity;
import com.yundong.milk.user.activity.RefundActivity;
import com.yundong.milk.user.activity.SettingActivity;
import com.yundong.milk.util.Const;
import com.yundong.milk.util.PreferencesUtils;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.view.ILoginView;
import com.yundong.milk.widget.CircleImageView;
import com.yundong.milk.widget.dialog.SweetAlertDialog;

/**
 * Created by lj on 2016/12/12.
 * 个人中心
 */
public class UserFragment extends BaseFragment implements View.OnClickListener, ILoginView {
    private CircleImageView imgUserHead;
    private TextView txtUserInfo;

    @Override
    public int getRootView() {
        return R.layout.fragment_user;
    }

    @Override
    public void onResume() {
        super.onResume();
        LoginPresenter
                .getInstance()
                .with(this)
                .login(YunDongApplication.getLoginBean().getData().getUserinfo().getPhone(), PreferencesUtils.getString(getActivity(), Const.LOGIN_PWD));
    }

    @Override
    public void initView(View view) {
        imgUserHead = (CircleImageView) view.findViewById(R.id.imgUserHead);
        imgUserHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SettingActivity.class));
            }
        });
        txtUserInfo = (TextView) view.findViewById(R.id.txtUserInfo);

        view.findViewById(R.id.txtUserInfo).setOnClickListener(this);
        view.findViewById(R.id.imageSetting).setOnClickListener(this);
        view.findViewById(R.id.imageRight).setOnClickListener(this);

        view.findViewById(R.id.txtWaitPay).setOnClickListener(this);
        view.findViewById(R.id.txtWaitReceipt).setOnClickListener(this);
        view.findViewById(R.id.txtWaitComment).setOnClickListener(this);
        view.findViewById(R.id.txtReturn).setOnClickListener(this);

        view.findViewById(R.id.txtOrder).setOnClickListener(this);
        view.findViewById(R.id.txtMineCollection).setOnClickListener(this);
        view.findViewById(R.id.txtAddress).setOnClickListener(this);
        view.findViewById(R.id.txtPlatform).setOnClickListener(this);
        view.findViewById(R.id.txtFeedBack).setOnClickListener(this);
        view.findViewById(R.id.reContact).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtUserInfo:
                startActivity(new Intent(getActivity(), OrderDetailActivity.class));
                break;
            case R.id.imageRight://消息中心
                startActivity(new Intent(getActivity(), MessageCenterActivity.class));
                break;
            case R.id.imageSetting://设置
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.txtWaitPay://待付款
                startActivity(new Intent(getActivity(), MineOrderActivity.class).putExtra("position", 1));
                break;
            case R.id.txtWaitReceipt://待收货
                startActivity(new Intent(getActivity(), MineOrderActivity.class).putExtra("position", 2));
                break;
            case R.id.txtWaitComment://待评论
                startActivity(new Intent(getActivity(), MineOrderActivity.class).putExtra("position", 3));
                break;
            case R.id.txtReturn://退货退款
                startActivity(new Intent(getActivity(), RefundActivity.class));
                break;
            case R.id.txtOrder://全部订单
                startActivity(new Intent(getActivity(), MineOrderActivity.class).putExtra("position", 0));
                break;
            case R.id.txtMineCollection://我的收藏
                startActivity(new Intent(getActivity(), MineCollectionActivity.class));
                break;
            case R.id.txtAddress://收货地址
                startActivity(new Intent(getActivity(), ReceiptAddressActivity.class));
                break;
            case R.id.txtPlatform://平台审核
                startActivity(new Intent(getActivity(), PlatformAuditActivity.class));
                break;
            case R.id.txtFeedBack://意见反馈
                startActivity(new Intent(getActivity(), FeedBackActivity.class));
                break;
            case R.id.reContact://拨打电话
                final SweetAlertDialog dialog = new SweetAlertDialog(mContext);
                dialog.showCancelButton(true);
                dialog.setTitleText(mContext.getString(R.string.whether_call));
                dialog.setCancelText(mContext.getString(R.string.cancel));
                dialog.setConfirmText(mContext.getString(R.string.confirm));
                dialog.show();
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent phoneIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + "4009953333"));
                        startActivity(phoneIntent);
                        dialog.dismiss();
                    }
                });
                break;
        }
    }

    @Override
    public void login(LoginBean baseReceiveBean) {
        Glide.with(getActivity()).load(baseReceiveBean.getData().getUserinfo().getAvatar()).into(imgUserHead);
        txtUserInfo.setText(baseReceiveBean.getData().getUserinfo().getUname());
    }

    @Override
    public void loginOnError(String e) {
        ToastUtil.showShortToast("获取用户信息失败");
    }
}
