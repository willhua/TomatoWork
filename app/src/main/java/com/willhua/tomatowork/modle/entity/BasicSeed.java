package com.willhua.tomatowork.modle.entity;

/**
 * Created by LYHua on 2016-11-17.
 */

public abstract class BasicSeed {
    protected int mType;
    protected String mDescribe;
    protected int mPriority;

    public int getPriority() {
        return mPriority;
    }

    public void setPriority(int priority) {
        mPriority = priority;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public String getDescribe() {
        return mDescribe;
    }

    public void setDescribe(String describe) {
        mDescribe = describe;
    }
}
