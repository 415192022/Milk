package com.yundong.milk.user.activity;

import android.os.Bundle;

import com.yundong.milk.R;
import com.yundong.milk.base.BaseActivity;

/**
 * Created by lx on 2017/2/16.
 */

public class ActivityCommentList extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);

        initTitle(R.string.commentList,true);
    }
}
