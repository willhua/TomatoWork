package com.willhua.tomatowork.modle.entity;

import java.util.List;

/**
 * Created by willhua on 2016-11-17.
 */

public class Candy extends BasicSeed{
    private final long mID;
    private String mTitle;
    private int mObjectiveTomato;
    private int mCurrentTomato;


    public Candy(String describe){
        mDescribe = describe;
        mID = System.currentTimeMillis();
    }
}
