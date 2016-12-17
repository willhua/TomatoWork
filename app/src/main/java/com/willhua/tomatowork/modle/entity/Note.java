package com.willhua.tomatowork.modle.entity;

/**
 * Created by willhua on 2016-11-17.
 */

public class Note extends BasicSeed{
    private long mID;
    private long mTime;
    private String mDescribe;

    public Note(String title, String describe){
        this(-1, title, describe, 0);
    }

    public Note(String title, String describe, int priority){
        this(-1, title, describe, priority);
    }

    public Note(long id, String title, String describe, long time){
        mID = id;
        mTitle = title;
        mDescribe = describe;
        mTime = time;
    }

    public void setDescribe(String describe){
        mDescribe = describe;
    }

    public String getDescribe(){
        return mDescribe;
    }

    public void setID(long id){
        mID = id;
    }

    public long getID(){
        return  mID;
    }
}
