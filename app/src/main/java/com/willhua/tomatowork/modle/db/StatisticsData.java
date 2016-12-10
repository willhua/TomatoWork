package com.willhua.tomatowork.modle.db;

import android.database.Cursor;

import com.willhua.tomatowork.modle.IModleStatistics;

/**
 * Created by willhua on 2016/12/6.
 */

public class StatisticsData implements IModleStatistics {

    @Override
    public int[] getMonthTomato(int year, int month) {
        Cursor cursor = DbMaster.getMaster().getReadableDatabase().query(TomatoTable.TABLE_NAME,
                new String[]{TomatoTable.KEY_DAY}, "WHERE " + TomatoTable.KEY_YEAR + "=? AND " + TomatoTable.KEY_MONTH + "=?",
                new String[]{Integer.toString(year), Integer.toString(month)}, TomatoTable.KEY_DAY, null, TomatoTable.KEY_DAY);
        int[] data  = new int[31];
        while (cursor.moveToNext()){
            int day = cursor.getInt(0);
            data[day - 1] += 1;
        }
        if(!cursor.isClosed()){
            cursor.close();
        }
        return data;
    }
}
