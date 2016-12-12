package com.willhua.tomatowork.presenter;

import com.willhua.tomatowork.core.CommandRunner;
import com.willhua.tomatowork.core.ICommand;
import com.willhua.tomatowork.modle.data.idata.IModleCandy;
import com.willhua.tomatowork.modle.data.CandyData;
import com.willhua.tomatowork.modle.entity.Candy;
import com.willhua.tomatowork.ui.iview.ICandyListView;
import com.willhua.tomatowork.utils.LogUtil;

import java.util.List;

/**
 * Created by willhua on 2016-11-17.
 */

public class CandyPresenter {
    private static final String TAG = "CandyPresenter";
    private ICandyListView mView;
    private IModleCandy mModleCandy;

    public CandyPresenter(ICandyListView iView) {
        mView = iView;
        mModleCandy = new CandyData();
    }

    public void showUnfinishedCandies() {
        LogUtil.d(TAG, "showUnfinishedCandies");
        CommandRunner.getRunner().runCommand(mGetUnfinishedCandiesCommand);
    }

    private ICommand mGetUnfinishedCandiesCommand = new ICommand() {
        private List<Candy> mCandies;

        @Override
        public void execute() {
            mCandies = mModleCandy.getAllUnfinishedCandy();
        }

        @Override
        public void updateUI() {
            LogUtil.d(TAG, "mGetUnfinishedCandiesCommand  updateUI size:" + mCandies.size());
            mView.onUnfinishedCandyQueried(mCandies);
        }
    };

    public void showAllCandies() {
        CommandRunner.getRunner().runCommand(mGetAllCandies);
    }

    private ICommand mGetAllCandies = new ICommand() {
        private List<Candy> mCandies;

        @Override
        public void execute() {
            mCandies = mModleCandy.getAllUnfinishedCandy();
            mCandies.addAll(mModleCandy.getAllFinishedCandy());
        }

        @Override
        public void updateUI() {
            mView.onFinishedCandyQueried(mCandies);
        }
    };

    public void addCandy(final Candy candy) {
        CommandRunner.getRunner().runCommand(new ICommand() {
            @Override
            public void execute() {
                mModleCandy.addCandy(candy);
            }

            @Override
            public void updateUI() {

            }
        });
    }
}
