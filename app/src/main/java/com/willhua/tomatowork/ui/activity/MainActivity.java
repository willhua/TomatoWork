package com.willhua.tomatowork.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.willhua.tomatowork.R;
import com.willhua.tomatowork.presenter.MainPresenter;
import com.willhua.tomatowork.ui.adapter.FunctionPagerAdapter;
import com.willhua.tomatowork.ui.fragment.TabFragment;

/**
 * Created by willhua on 2016/11/13.
 */

public class MainActivity extends BaseActivity implements TabFragment.TabSelected {

    private Toolbar mToolbar;
    private MainPresenter mMainPresenter;
    private ViewPager mViewPager;
    private TabFragment mTabFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initView();
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new FunctionPagerAdapter(this));
    }

    private void initView(){
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.menu_main);
    }

    @Override
    public void onStartTomato() {
        Toast.makeText(getBaseContext(), "start", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowCandy() {

    }

    @Override
    public void onShowStatistics() {

    }

    @Override
    public void onShowUser() {

    }
}
