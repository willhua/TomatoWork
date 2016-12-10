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
import com.willhua.tomatowork.core.CommandRunner;
import com.willhua.tomatowork.modle.db.DbMaster;
import com.willhua.tomatowork.modle.entity.Tomato;
import com.willhua.tomatowork.ui.fragment.NoteListFragment;
import com.willhua.tomatowork.ui.iview.IView;
import com.willhua.tomatowork.ui.adapter.FunctionPagerAdapter;
import com.willhua.tomatowork.ui.fragment.StatisticsFragment;
import com.willhua.tomatowork.ui.fragment.TabFragment;
import com.willhua.tomatowork.utils.Constants;
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

    private int mTomatoTime = 25;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        DbMaster.init(getApplicationContext());
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
    }

    private void initView(){
        mToolbar.inflateMenu(R.menu.menu_main);
    }

    @Override
    protected void onDestroy() {
        CommandRunner.getRunner().release();
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
        mViewPager.setCurrentItem(Constants.POSITOIN_NOTE);
/*        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, new NoteListFragment());
        ft.commit();*/
    }

    @Override
    public void onShowStatistics() {
        LogUtil.d(TAG, "onShowStatistics");
        mViewPager.setCurrentItem(Constants.POSITOIN_STAT);
/*        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, new StatisticsFragment());
        ft.commit();*/
    }

    @Override
    public void onShowUser() {
        mViewPager.setCurrentItem(Constants.POSITOIN_USER);
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
