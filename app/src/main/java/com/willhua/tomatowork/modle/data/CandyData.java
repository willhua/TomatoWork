package com.willhua.tomatowork.modle.data;

import android.content.ContentValues;
import android.database.Cursor;

import com.willhua.tomatowork.modle.data.idata.IModleCandy;
import com.willhua.tomatowork.modle.entity.Candy;
import com.willhua.tomatowork.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willhua on 2016/11/29.
 */

public class CandyData extends AbstractModle implements IModleCandy{
    private static final String TAG = "CandyData";
    private static final String ORDER_BY = CandyTable.KEY_MODIFY_TIME + " DESC ";

    private final String[] COLUMNS = new String[]{CandyTable.KEY_ID, CandyTable.KEY_TITLE,
            CandyTable.KEY_DESCRIBE, CandyTable.KEY_CURRENT_TOM,
            CandyTable.KEY_CURRENT_TOM, CandyTable.KEY_OBJECTIVE_TOM,
            CandyTable.KEY_PRIORITY};

    public CandyData() {
    }

    public void addCandy(Candy candy) {
        LogUtil.d(TAG, "addCandy " + candy.getTitle());
        final ContentValues values = new ContentValues();
        values.put(CandyTable.KEY_TITLE, candy.getTitle());
        values.put(CandyTable.KEY_CURRENT_TOM, 0);
        values.put(CandyTable.KEY_OBJECTIVE_TOM, candy.getObjectiveTomato());
        values.put(CandyTable.KEY_TYPE, candy.getType());
        values.put(CandyTable.KEY_STATE, CandyTable.STATE_UNFINISHED);
        values.put(CandyTable.KEY_MODIFY_TIME, System.currentTimeMillis());
        long id = DbMaster.getMaster().insert(CandyTable.TABLE_NAME, null, values);
        candy.setID(id);
    }

    public void updateCandy(Candy candy) {
        final ContentValues values = new ContentValues();
        values.put(CandyTable.KEY_TITLE, candy.getTitle());
        values.put(CandyTable.KEY_CURRENT_TOM, candy.getCurrentTomato());
        values.put(CandyTable.KEY_OBJECTIVE_TOM, candy.getObjectiveTomato());
        values.put(CandyTable.KEY_TYPE, candy.getType());
        values.put(CandyTable.KEY_MODIFY_TIME, System.currentTimeMillis());
        if(candy.getObjectiveTomato() == candy.getCurrentTomato()){
            values.put(CandyTable.KEY_STATE, CandyTable.STATE_FINISHED);
        }else{
            values.put(CandyTable.KEY_STATE, CandyTable.STATE_UNFINISHED);
        }
        DbMaster.getMaster().update(CandyTable.TABLE_NAME,
                values, CandyTable.KEY_ID + "=?", new String[]{Long.toString(candy.getID())});
    }

    public void deleteCandy(final long id){
        DbMaster.getMaster().delete(CandyTable.TABLE_NAME,
                CandyTable.KEY_ID + "=?", new String[]{Long.toString(id)});
    }

    public List<Candy> getAllUnfinishedCandy(){
        return queryCnadies(CandyTable.STATE_UNFINISHED);
    }

    public List<Candy> getAllFinishedCandy(){
        return queryCnadies(CandyTable.STATE_FINISHED);
    }

    private List<Candy> queryCnadies(final String state){
        List<Candy> candies = new ArrayList<>();
        Cursor cursor = DbMaster.getMaster().query(CandyTable.TABLE_NAME, COLUMNS,
                CandyTable.KEY_STATE + "=?", new String[]{state},
                null, null, ORDER_BY);
        LogUtil.d(TAG, "queryCnadies cursor.size:" + cursor.getCount());
        if (cursor != null && cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Candy candy = new Candy(cursor.getString(cursor.getColumnIndex(CandyTable.KEY_DESCRIBE)));
                candy.setID(cursor.getLong(cursor.getColumnIndex(CandyTable.KEY_ID)));
                candy.setObjectiveTomato(cursor.getInt(cursor.getColumnIndex(CandyTable.KEY_OBJECTIVE_TOM)));
                candy.setCurrentTomato(cursor.getInt(cursor.getColumnIndex(CandyTable.KEY_CURRENT_TOM)));
                candy.setTitle(cursor.getString(cursor.getColumnIndex(CandyTable.KEY_TITLE)));
                candy.setPriority(cursor.getInt(cursor.getColumnIndex(CandyTable.KEY_PRIORITY)));
                candies.add(candy);
            }
        }
        if(cursor != null){
            cursor.close();
        }
        return candies;
    }
}
