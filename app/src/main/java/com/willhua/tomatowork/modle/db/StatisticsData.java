package com.willhua.tomatowork.modle.db;

import android.database.Cursor;

import com.willhua.tomatowork.modle.IModleStatistics;
import com.willhua.tomatowork.utils.LogUtil;

/**
 * Created by willhua on 2016/12/6.
 */

public class StatisticsData implements IModleStatistics {
    private static final String TAG = "StatisticsData";

    @Override
    public int[] getMonthTomato(int year, int month) {
        LogUtil.d(TAG, "getMonthTomato  CURSOR c");
        Cursor cursor = DbMaster.getMaster().getWritableDatabase().query(TomatoTable.TABLE_NAME,
                new String[]{TomatoTable.KEY_DAY}, TomatoTable.KEY_YEAR + "=? AND " + TomatoTable.KEY_MONTH + "=?",
                new String[]{Integer.toString(year), Integer.toString(month)}, null, null, null);
        int[] data = new int[31];
        LogUtil.d(TAG, "getMonthTomato  CURSOR count:" + cursor.getCount());
        while (cursor.moveToNext()) {
            int day = cursor.getInt(0);
            data[day - 1] += 1;
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        return data;
    }
}
