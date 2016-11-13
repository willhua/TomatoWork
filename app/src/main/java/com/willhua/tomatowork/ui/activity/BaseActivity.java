package com.willhua.tomatowork.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import com.willhua.tomatowork.utils.LogUtil;

/**
 * Created by lisan on 2016/11/13.
 */

public class BaseActivity extends Activity {

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
