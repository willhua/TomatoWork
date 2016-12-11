package com.willhua.tomatowork.modle;

/**
 * Created by willhua on 2016/12/6.
 */

public interface IModleTomato {
    int[] getMonthTomato(int year, int month);
    void insertTomato(int year, int month, int day, int hour);
}
