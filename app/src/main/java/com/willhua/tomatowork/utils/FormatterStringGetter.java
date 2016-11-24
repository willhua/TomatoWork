package com.willhua.tomatowork.utils;

/**
 * Created by willhua on 2016/11/24.
 */

/**
 * this should be used on main thread because of mStringBuilder is not synchronized
 */
public class FormatterStringGetter {

    private static StringBuilder mStringBuilder = new StringBuilder();
    private FormatterStringGetter(){}

    public static String getTomatoTime(int time){
        mStringBuilder.delete(0, mStringBuilder.length());
        int minute = time / 60;
        int seconds = time % 60;
        if(minute < 10){
            mStringBuilder.append(0).append(minute);
        }else{
            mStringBuilder.append(minute);
        }
        mStringBuilder.append(":");
        if(seconds < 10){
            mStringBuilder.append(0).append(seconds);
        }else{
            mStringBuilder.append(seconds);
        }
        return mStringBuilder.toString();
    }
}
