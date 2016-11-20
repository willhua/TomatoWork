package com.willhua.tomatowork.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.willhua.tomatowork.R;
import com.willhua.tomatowork.presenter.MainPresenter;
import com.willhua.tomatowork.ui.adapter.FunctionPagerAdapter;

/**
 * Created by willhua on 2016/11/13.
 */

public class MainActivity extends BaseActivity {

    private Toolbar mToolbar;
    private MainPresenter mMainPresenter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initToolbar();
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new FunctionPagerAdapter(this));
    }

    private void initToolbar(){
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.menu_main);
    }

}
