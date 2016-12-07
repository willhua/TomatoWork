package com.willhua.tomatowork.ui.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.willhua.tomatowork.R;
import com.willhua.tomatowork.core.ICommandRunner;
import com.willhua.tomatowork.core.ProcessingService;
import com.willhua.tomatowork.modle.entity.Tomato;
import com.willhua.tomatowork.ui.iview.IView;
import com.willhua.tomatowork.ui.adapter.FunctionPagerAdapter;
import com.willhua.tomatowork.ui.fragment.StatisticsFragment;
import com.willhua.tomatowork.ui.fragment.TabFragment;
import com.willhua.tomatowork.utils.LogUtil;
import com.willhua.tomatowork.utils.Utils;

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

    private Handler mHandler;
    private ICommandRunner mCommandRunner;

    private int mTomatoTime = 25;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        bindService(new Intent(MainActivity.this, ProcessingService.class), mServiceConnection, BIND_AUTO_CREATE);
        initView();
        initData();
    }

    private void initData(){
        mViewPager.setAdapter(new FunctionPagerAdapter(this));
        mHandler = new Handler(mHandlerCallback);
        Tomato.getInstance().setMinutes(mTomatoTime);
        Tomato.getInstance().setTomatoEvent(mTomatoEvent);
    }

    private void initView(){
        mToolbar.inflateMenu(R.menu.menu_main);
    }

    public ICommandRunner getCommandRunner(){
        return mCommandRunner;
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.d(TAG, "onServiceConnected " + service);
            mCommandRunner = ((ProcessingService.LocalCommandRunner)service).getCommandRunner();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.d(TAG, "onServiceDisconnected " + name);
            mCommandRunner = null;
        }
    };

    @Override
    protected void onDestroy() {
        unbindService(mServiceConnection);
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

    }

    @Override
    public void onShowStatistics() {
        LogUtil.d(TAG, "onShowStatistics");
        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
        fm.replace(R.id.container, new StatisticsFragment());
        fm.commit();
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

}
