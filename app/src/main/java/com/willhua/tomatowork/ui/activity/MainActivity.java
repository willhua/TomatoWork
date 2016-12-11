package com.willhua.tomatowork.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.TextView;

import com.willhua.tomatowork.R;
import com.willhua.tomatowork.core.CommandRunner;
import com.willhua.tomatowork.core.Tomato;
import com.willhua.tomatowork.modle.db.DbMaster;
import com.willhua.tomatowork.ui.adapter.FunctionPagerAdapter;
import com.willhua.tomatowork.ui.fragment.TabFragment;
import com.willhua.tomatowork.ui.iview.IView;
import com.willhua.tomatowork.ui.view.TomatoFinishPopupWindow;
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
    private static final int MSG_INVALIDATE_TOMATO_OVER = 102;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_text)
    TextView mTabText;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    private Handler mHandler;

    private int mTomatoTime = 1;

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
        Tomato.getInstance().setMinutes(mTomatoTime);
        Tomato.getInstance().registerTomatoEvent(mTomatoEvent);
    }

    private void initView() {
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
            Message msg = Message.obtain();
            msg.what = MSG_INVALIDATE_TOMATO_OVER;
            mHandler.sendMessage(msg);
        }
    };

    private Handler.Callback mHandlerCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_INVALIDATE_TOMATO_TIME:
                    mTabText.setText(Utils.getTomatoTime(msg.arg1));
                    break;
                case MSG_INVALIDATE_TOMATO_OVER:
                    mTabText.setText("25:00");
                    TomatoFinishPopupWindow popupWindow = new TomatoFinishPopupWindow(getApplicationContext());
                    popupWindow.showAtLocation(mViewPager, Gravity.BOTTOM, 0, 0);
                default:
                    break;
            }
            return true;
        }
    };

}
