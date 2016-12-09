package com.willhua.tomatowork.presenter;

import android.content.Context;

import com.willhua.tomatowork.core.CommandRunner;
import com.willhua.tomatowork.core.ICommand;
import com.willhua.tomatowork.modle.IModleStatistics;
import com.willhua.tomatowork.ui.iview.IStatisticsView;

/**
 * Created by willhua on 2016/12/6.
 */

public class StatisticsPresenter {
    private IModleStatistics mModleStatistics;

    public StatisticsPresenter(IStatisticsView statisticsView){

    }

    public void getMouthTomato(int year, int mouth){
        CommandRunner.getRunner().runCommand(new ICommand() {
            @Override
            public void execute() {

            }

            @Override
            public void updateUI() {

            }
        });
    }
}
