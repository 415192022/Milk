package com.yundong.milk.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yundong.milk.R;

/**
 * Created by lj on 2016/10/31.
 */
public abstract class BaseFragment extends Fragment{

    public Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getRootView(), null);
        initView(view);
        return view;
    }

    public abstract int getRootView();

    public abstract void initView(View view);

    public void noNetWork(){
        Toast.makeText(mContext, getString(R.string.net_not_available), Toast.LENGTH_SHORT).show();
    }

//    public void showMsg(String msg){
//        AppMsg.Style style = new AppMsg.Style(AppMsg.LENGTH_SHORT, R.color.colorPrimary);
//        AppMsg appMsg =  AppMsg.makeText(getActivity(), msg, style);
//        appMsg.setAnimation(android.R.anim.fade_in, android.R.anim.fade_out);
//        appMsg.setLayoutGravity(BOTTOM);
//        appMsg.show();
//    }

    public void errorMsg(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
//        AppMsg.cancelAll();
    }
}
