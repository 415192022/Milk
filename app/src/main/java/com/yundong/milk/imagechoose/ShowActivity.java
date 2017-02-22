package com.yundong.milk.imagechoose;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;

import com.yundong.milk.R;
import com.yundong.milk.imagechoose.view.ScaleImageView;

public class ShowActivity extends ActionBarActivity {

    private ScaleImageView mScaleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.choose_show);

        String path = getIntent().getStringExtra("path");

        mScaleImageView = (ScaleImageView) findViewById(R.id.mScaleImageView);

        Bitmap loacalBitmap = ChooseImage.getLoacalBitmap(path);
        mScaleImageView.setImageBitmap(loacalBitmap);
    }

 
}
