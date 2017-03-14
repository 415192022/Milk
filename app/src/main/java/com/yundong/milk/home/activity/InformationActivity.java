package com.yundong.milk.home.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.home.adapter.InformationListAdapter;
import com.yundong.milk.model.LettersBean;
import com.yundong.milk.present.HomeFragmentPresenter;
import com.yundong.milk.view.ILettersView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lj on 2017/1/5.
 * 资讯
 */
public class InformationActivity extends BaseActivity  implements ILettersView{

    private RecyclerView rv_mycollection;
    private InformationListAdapter mAdapter;

    private HomeFragmentPresenter homeFragmentPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_list);
        initTitle(R.string.milkInformation, true);
        rv_mycollection = (RecyclerView) findViewById(R.id.rv_mycollection);
        rv_mycollection.setLayoutManager(new LinearLayoutManager(this));
        rv_mycollection.setHasFixedSize(true);

        mAdapter = new InformationListAdapter(this);
        rv_mycollection.setAdapter(mAdapter);


        homeFragmentPresenter = HomeFragmentPresenter.getInstance().with(this);
        homeFragmentPresenter.getLetters("1", "desc");
    }

    @Override
    public void getLetters(LettersBean baseReceiveBean) {
        Observable.from(baseReceiveBean.getData().getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LettersBean.LettersDataO.LettersDataA>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LettersBean.LettersDataO.LettersDataA lettersDataA) {
                        mAdapter.getLettersDataAs().add(lettersDataA);
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void getLettersOnError(String e) {

    }
}
