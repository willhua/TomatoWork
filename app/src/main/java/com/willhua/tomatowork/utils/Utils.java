package com.willhua.tomatowork.utils;

/**
 * Created by willhua on 2016/11/24.
 */

import android.content.Context;

import com.willhua.tomatowork.modle.entity.Candy;

/**
 * this should be used on main thread because of mStringBuilder is not synchronized
 */
public class Utils {

    private static StringBuilder mStringBuilder = new StringBuilder();

    private Utils() {
    }

    /**
     * @param time the unit is second
     * @return
     */
    public static String getTomatoTime(int time) {
        mStringBuilder.delete(0, mStringBuilder.length());
        int minute = time / 60;
        int seconds = time % 60;
        if (minute < 10) {
            mStringBuilder.append(0);
        }
        mStringBuilder.append(minute);
        mStringBuilder.append(":");
        if (seconds < 10) {
            mStringBuilder.append(0);
        }
        mStringBuilder.append(seconds);
        return mStringBuilder.toString();
    }

    public static String getFormatCandyInfo(Candy candy){
        if(candy != null){
            return mStringBuilder.delete(0, mStringBuilder.length()).append(candy.getTitle()).append(" (").append(candy.getCurrentTomato())
                    .append("/").append(candy.getObjectiveTomato()).append(")").toString();
        }
        return "";
    }

    public static int getStatusBarHeight(Context context) {
        int height = 0;
        int resID = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resID > 0) {
            height = (int) context.getResources().getDimension(resID);
        }
        return height;
    }

    public static float[] intArrayToFloatArray(int[] data) {
        if (data != null) {
            float[] dataf = new float[data.length];
            for (int i = 0; i < data.length; i++) {
                dataf[i] = data[i];
            }
            return dataf;
        }
        return new float[0];
    }
}
