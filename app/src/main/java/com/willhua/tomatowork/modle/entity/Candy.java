package com.willhua.tomatowork.modle.entity;

/**
 * Created by willhua on 2016-11-17.
 */

public class Candy extends BasicSeed{
    private long mID;
    private int mObjectiveTomato;
    private int mCurrentTomato;

    public Candy(String title){
        mTitle = title;
    }

    public int getObjectiveTomato() {
        return mObjectiveTomato;
    }

    public void setObjectiveTomato(int objectiveTomato) {
        mObjectiveTomato = objectiveTomato;
    }

    public int getCurrentTomato() {
        return mCurrentTomato;
    }

    public void setCurrentTomato(int currentTomato) {
        mCurrentTomato = currentTomato;
    }

    public long getID() {
        return mID;
    }

    public long setID(long id) {
        return mID = id;
    }

    @Override
    public String toString(){
        return mTitle + " " + mCurrentTomato + "/" + mObjectiveTomato;
    }

    public void addTomato(){
        ++mCurrentTomato;
    }

}
