package com.willhua.tomatowork.modle.data;

import android.content.ContentValues;
import android.database.Cursor;

import com.willhua.tomatowork.modle.data.idata.IModleTomato;
import com.willhua.tomatowork.utils.LogUtil;

/**
 * Created by willhua on 2016/12/6.
 */

public class TomatoData extends AbstractModle implements IModleTomato {
    private static final String TAG = "TomatoData";

    @Override
    public int[] getMonthTomato(int year, int month) {
        LogUtil.d(TAG, "getMonthTomato  CURSOR c");
        Cursor cursor = DbMaster.getMaster().query(TomatoTable.TABLE_NAME,
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

    @Override
    public void insertTomato(int year, int month, int day, int hour) {
        LogUtil.d(TAG, "insertTomato  " + hour);
        ContentValues values = new ContentValues();
        values.put(TomatoTable.KEY_HOUR, hour);
        values.put(TomatoTable.KEY_DAY, day);
        values.put(TomatoTable.KEY_MONTH, month);
        values.put(TomatoTable.KEY_YEAR, year);
        DbMaster.getMaster().insert(TomatoTable.TABLE_NAME, null, values);
    }


}
