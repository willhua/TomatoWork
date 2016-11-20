package com.willhua.tomatowork.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.willhua.tomatowork.utils.LogUtil;

/**
 * Created by willhua on 2016/11/13.
 */

public class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        LogUtil.d(getClass().getSimpleName(), " onCreate");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        LogUtil.d(getClass().getSimpleName(), " onDestory");
    }
}
