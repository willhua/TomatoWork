package com.willhua.tomatowork.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.willhua.tomatowork.R;
import com.willhua.tomatowork.modle.entity.Candy;
import com.willhua.tomatowork.modle.entity.Tomato;
import com.willhua.tomatowork.presenter.CandyPresenter;
import com.willhua.tomatowork.ui.IView;
import com.willhua.tomatowork.ui.adapter.FunctionPagerAdapter;
import com.willhua.tomatowork.ui.fragment.TabFragment;
import com.willhua.tomatowork.utils.Utils;
import com.willhua.tomatowork.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by willhua on 2016/11/13.
 */

public class MainActivity extends BaseActivity implements IView, TabFragment.TabSelected {

    private static final String TAG = "MainActivity";
    private static final int MSG_INVALIDATE_TOMATO_TIME = 101;

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.viewpager) ViewPager mViewPager;
    @BindView(R.id.toolbar_text) TextView mTabText;


    private CandyPresenter mCandyPresenter;
    private TabFragment mTabFragment;
    private Handler mHandler;

    private int mTomatoTime = 25;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    private void initData(){
        mViewPager.setAdapter(new FunctionPagerAdapter(this));
        mHandler = new Handler(mHandlerCallback);
        Tomato.getInstance().setMinutes(mTomatoTime);
        Tomato.getInstance().setTomatoEvent(mTomatoEvent);

        mCandyPresenter = new CandyPresenter(this, getApplicationContext());
    }

    private void initView(){
        mToolbar.inflateMenu(R.menu.menu_main);
    }

    @Override
    public void onStartTomato() {
        Toast.makeText(getBaseContext(), "start", Toast.LENGTH_SHORT).show();
        Tomato.getInstance().startTomato();
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

    private Tomato.TomatoEvent mTomatoEvent = new Tomato.TomatoEvent() {
        @Override
        public void onSecond(int left) {
            Message msg = Message.obtain();
            msg.what = MSG_INVALIDATE_TOMATO_TIME;
            msg.arg1 = left;
            mHandler.sendMessage(msg);
        }

        @Override
        public void onOver() {

        }
    };

    private Handler.Callback mHandlerCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case MSG_INVALIDATE_TOMATO_TIME:
                    mTabText.setText(Utils.getTomatoTime(msg.arg1));
                    break;
                default:
                    break;
            }
            return true;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LogUtil.d(TAG, "onkeydown");
        if(keyCode == KeyEvent.KEYCODE_ENTER){
            mCandyPresenter.addCandy(new Candy("test1"));
        }
        return super.onKeyDown(keyCode, event);
    }
}
