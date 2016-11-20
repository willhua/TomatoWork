package com.willhua.tomatowork.presenter;

import com.willhua.tomatowork.modle.entity.Candy;
import com.willhua.tomatowork.ui.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willhua on 2016-11-17.
 */

public class CandyPresenter {
    private IView mView;

    public List<Candy> getCandies(){
        List<Candy> candies = new ArrayList<>();
        candies.add(new Candy("First"));
        candies.add(new Candy("Sec"));
        candies.add(new Candy("Thr"));
        candies.add(new Candy("Four"));
        candies.add(new Candy("Five"));
        return  candies;
    }
}
