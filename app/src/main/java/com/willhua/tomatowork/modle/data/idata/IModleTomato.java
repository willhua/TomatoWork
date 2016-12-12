package com.willhua.tomatowork.modle.data.idata;

/**
 * Created by willhua on 2016/12/6.
 */

public interface IModleTomato extends IModle {
    int[] getMonthTomato(int year, int month);
    void insertTomato(int year, int month, int day, int hour);
}
