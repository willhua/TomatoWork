package com.willhua.tomatowork.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.willhua.tomatowork.R;

/**
 * Created by willhua on 2016/11/13.
 */

public class MainActivity extends BaseActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initToolbar();




    }

    private void initToolbar(){
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mToolbar.setLogo(R.mipmap.ic_launcher);
        mToolbar.setTitle("Title");
        mToolbar.setSubtitle("SubTitle");

        mToolbar.inflateMenu(R.menu.menu_main);
    }

}
