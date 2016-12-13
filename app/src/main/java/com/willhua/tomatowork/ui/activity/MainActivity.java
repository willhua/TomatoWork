package com.willhua.tomatowork.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.willhua.tomatowork.R;
import com.willhua.tomatowork.core.CommandRunner;
import com.willhua.tomatowork.core.ICommand;
import com.willhua.tomatowork.modle.data.DbMaster;
import com.willhua.tomatowork.ui.adapter.FunctionPagerAdapter;
import com.willhua.tomatowork.ui.fragment.TabFragment;
import com.willhua.tomatowork.ui.iview.IView;
import com.willhua.tomatowork.ui.view.TomatoFinishPopupWindow;
import com.willhua.tomatowork.utils.Constants;
import com.willhua.tomatowork.utils.LogUtil;
import com.willhua.tomatowork.utils.Utils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by willhua on 2016/11/13.
 */

public class MainActivity extends BaseActivity implements IView, TabFragment.TabSelected {

    private static final String TAG = "MainActivity";
    private static final int FLAG_START_TOMATO = 0;
    private static final int FLAG_NOT_DURING_TOMATO = -1;
    private static final int MSG_INVALIDATE_TOMATO_TIME = 101;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_text)
    TextView mTabText;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.fab_start)
    FloatingActionButton mFabStart;

    private Handler mHandler;

    private Timer mTimer;
    private int mTomatoTime = 3;
    private int mContinueTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbMaster.init(getApplicationContext());
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        mViewPager.setAdapter(new FunctionPagerAdapter(this));
        mHandler = new Handler(mHandlerCallback);
        mContinueTime = FLAG_NOT_DURING_TOMATO;
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(MSG_INVALIDATE_TOMATO_TIME);
            }
        }, 0, 1000);
    }

    private void initView() {
        mToolbar.inflateMenu(R.menu.menu_main);
    }

    @Override
    protected void onDestroy() {
        CommandRunner.getRunner().release();
        mTimer.cancel();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onShowCandy() {
        mViewPager.setCurrentItem(Constants.POSITOIN_CANDY);
    }

    @Override
    public void onShowNote() {
        LogUtil.d(TAG, "onShowNote");
        mViewPager.setCurrentItem(Constants.POSITOIN_NOTE);
    }

    @Override
    public void onShowStatistics() {
        LogUtil.d(TAG, "onShowStatistics");
        mViewPager.setCurrentItem(Constants.POSITOIN_STAT);
    }

    @Override
    public void onShowUser() {
        mViewPager.setCurrentItem(Constants.POSITOIN_USER);
    }

    private Handler.Callback mHandlerCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (MainActivity.this.isDestroyed()) {
                return false;
            }
            switch (msg.what) {
                case MSG_INVALIDATE_TOMATO_TIME:
                    if (mContinueTime >= FLAG_START_TOMATO) {
                        ++mContinueTime;
                        if (mContinueTime == mTomatoTime) { //tomato finish
                            mContinueTime = FLAG_NOT_DURING_TOMATO;
                            mTabText.setText(Utils.getTomatoTime(mTomatoTime));
                            TomatoFinishPopupWindow popupWindow = new TomatoFinishPopupWindow(getApplicationContext());
                            popupWindow.showAtLocation(mViewPager, Gravity.BOTTOM, 0, 0);
                        } else {
                            mTabText.setText(Utils.getTomatoTime(mTomatoTime - mContinueTime));
                        }
                    }
                    break;
                default:
                    break;
            }
            return true;
        }
    };

    @OnClick(R.id.fab_start)
    public void startTomato(View view) {
        LogUtil.d(TAG, "fab click");
        if(mContinueTime == FLAG_NOT_DURING_TOMATO){
            mContinueTime = FLAG_START_TOMATO;
        }
    }

}
