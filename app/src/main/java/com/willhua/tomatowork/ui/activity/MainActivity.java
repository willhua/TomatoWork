package com.willhua.tomatowork.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;

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
        ViewGroup container = (ViewGroup)findViewById(R.id.main_container);
        LayoutInflater inflater = getLayoutInflater();
        inflater.inflate(R.layout.main_list, container);



    }

    private void initToolbar(){
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.menu_main);
    }

}
