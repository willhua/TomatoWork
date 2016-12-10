package com.willhua.tomatowork.presenter;

import com.willhua.tomatowork.core.CommandRunner;
import com.willhua.tomatowork.core.ICommand;
import com.willhua.tomatowork.modle.IModleStatistics;
import com.willhua.tomatowork.modle.db.StatisticsData;
import com.willhua.tomatowork.ui.iview.IStatisticsView;
import com.willhua.tomatowork.utils.LogUtil;

/**
 * Created by willhua on 2016/12/6.
 */

public class StatisticsPresenter {
    private static final String TAG = "StatisticsPresenter";
    private IModleStatistics mModleStatistics;
    private IStatisticsView mView;

    public StatisticsPresenter(IStatisticsView statisticsView) {
        mView = statisticsView;
        mModleStatistics = new StatisticsData();
    }

    public void getMouthTomato(final int year, final int month) {
        CommandRunner.getRunner().runCommand(new ICommand() {
            int[] data;

            @Override
            public void execute() {
                LogUtil.d(TAG, "presenter execute getMouthTomato " + month);
                data = mModleStatistics.getMonthTomato(year, month);
            }

            @Override
            public void updateUI() {
                LogUtil.d(TAG, "presenter updateUI " + month);
                mView.onMouthTomatoGot(year, month, data);
            }
        });
    }
}
