package com.yundong.milk.home.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.baidu.mapapi.map.Text;
import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.model.LetterDetailBean;
import com.yundong.milk.model.LettersBean;
import com.yundong.milk.present.InformationDetailActivityPresenter;
import com.yundong.milk.util.TimeUtils;
import com.yundong.milk.util.rxbus.RxBus;
import com.yundong.milk.util.rxbus.Subscribe;
import com.yundong.milk.util.rxbus.ThreadMode;
import com.yundong.milk.view.ILetterDetailView;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.OnUrlClickListener;

/**
 * Created by lj on 2017/1/5.
 * 资讯详情
 */
public class InformationDetailActivity extends BaseActivity implements ILetterDetailView{
    private InformationDetailActivityPresenter informationDetailActivityPresenter;

    private TextView txtTitle;
    private TextView txtContent;
    private TextView txtTime;

    @Override
    protected void onStart() {
        super.onStart();
        RxBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getDefault().unRegister(this);
    }

    private LettersBean.LettersDataO.LettersDataA lettersDataA;

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void receiveLetterDetail(LettersBean.LettersDataO.LettersDataA lettersDataA){
        this.lettersDataA=lettersDataA;
        informationDetailActivityPresenter=InformationDetailActivityPresenter.getInstance().with(this);
        informationDetailActivityPresenter.getLetterDetail(lettersDataA.getArticle_id());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_detail);
        txtTitle= (TextView) findViewById(R.id.txtTitle);
        txtContent= (TextView) findViewById(R.id.txtContent);
        txtTime= (TextView) findViewById(R.id.txtTime);
        initTitle(R.string.milkInformation, true);
    }

    @Override
    public void letterDetail(LetterDetailBean letterDetailBean) {
        txtTitle.setText(letterDetailBean.getData().getArticle_title());
        RichText.from(letterDetailBean.getData().getArticle_content()).clickable(true).urlClick(new OnUrlClickListener() {
            @Override
            public boolean urlClicked(String url) {
                return false;
            }
        }).into(txtContent);
//        txtContent.setText(letterDetailBean.getData().getArticle_content());
        txtTime.setText(TimeUtils.getTimeString_(letterDetailBean.getData().getArticle_time()));
    }

    @Override
    public void letterDetailOnError(String e) {

    }
}
