package com.willhua.tomatowork.presenter;

import com.willhua.tomatowork.core.CommandRunner;
import com.willhua.tomatowork.core.ICommand;
import com.willhua.tomatowork.modle.IModleCandy;
import com.willhua.tomatowork.modle.db.CandyData;
import com.willhua.tomatowork.modle.entity.Candy;
import com.willhua.tomatowork.ui.iview.ICandyListView;

import java.util.List;

/**
 * Created by willhua on 2016-11-17.
 */

public class CandyPresenter {
    private ICandyListView mView;
    private IModleCandy mModleCandy;

    public CandyPresenter(ICandyListView iView){
        mView = iView;
        mModleCandy = new CandyData();
    }

    public void showUnfinishedCandies(){
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
            mView.onUnfinishedCandyQueried(mCandies);
        }
    };

    public void showAllCandies(){
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

    public void addCandy(Candy candy){
        mModleCandy.addCandy(candy);
    }
}
