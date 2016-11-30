package com.willhua.tomatowork.presenter;

import android.content.Context;

import com.willhua.tomatowork.modle.IModleCandy;
import com.willhua.tomatowork.modle.dao.CandyData;
import com.willhua.tomatowork.modle.dao.TomatoDbOpenHelper;
import com.willhua.tomatowork.modle.entity.Candy;
import com.willhua.tomatowork.ui.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willhua on 2016-11-17.
 */

public class CandyPresenter {
    private IView mView;
    private IModleCandy mModleCandy;

    public CandyPresenter(IView iView, Context context){
        mView = iView;
        mModleCandy = new CandyData(new TomatoDbOpenHelper(context));
    }

    public List<Candy> getUnfinishedCandies(){
        return mModleCandy.getAllUnfinishedCandy();
    }

    public List<Candy> getAllCandies(){
        List<Candy> candies = new ArrayList<>();
        candies.addAll(mModleCandy.getAllUnfinishedCandy());
        candies.addAll(mModleCandy.getAllFinishedCandy());
        return candies;
    }

    public void addCandy(Candy candy){
        mModleCandy.addCandy(candy);
    }
}
