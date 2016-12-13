package com.willhua.tomatowork.presenter;

import com.willhua.tomatowork.core.CommandRunner;
import com.willhua.tomatowork.core.ICommand;
import com.willhua.tomatowork.modle.data.IObserver;
import com.willhua.tomatowork.modle.data.idata.IModleTomato;
import com.willhua.tomatowork.modle.data.TomatoData;
import com.willhua.tomatowork.ui.iview.IStatisticsView;
import com.willhua.tomatowork.utils.LogUtil;

/**
 * Created by willhua on 2016/12/6.
 */

public class TomatoPresenter implements IPresenter{
    private static final String TAG = "TomatoPresenter";
    private IModleTomato mModleTomato;
    private IStatisticsView mView;
    private int mCurrentYear = 0;
    private int mCurrentMonth = 0;

    public TomatoPresenter(IStatisticsView statisticsView) {
        mView = statisticsView;
        mModleTomato = new TomatoData();
    }

    public void getMonthTomato(final int year, final int month) {
        mCurrentMonth = month;
        mCurrentYear = year;
        CommandRunner.getRunner().runCommand(new ICommand() {
            int[] data;

            @Override
            public void execute() {
                LogUtil.d(TAG, "presenter execute getMonthTomato " + month);
                data = mModleTomato.getMonthTomato(year, month);
            }

            @Override
            public void updateUI() {
                LogUtil.d(TAG, "presenter updateUI " + month);
                mView.onMouthTomatoGot(year, month, data);
            }
        });
    }

    @Override
    public void onViewCreate() {
        mModleTomato.registerOberver(mObserver);
    }

    @Override
    public void onViewResume() {
    }

    private IObserver mObserver = new IObserver() {
        @Override
        public void onDataChanged(String table) {
            getMonthTomato(mCurrentYear, mCurrentMonth);
        }
    };

    @Override
    public void onViewPause() {
    }

    @Override
    public void onViewDestory() {
        mModleTomato.unregisterObserver();
    }
}
