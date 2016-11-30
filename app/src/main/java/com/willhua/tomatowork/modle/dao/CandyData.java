package com.willhua.tomatowork.modle.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.willhua.tomatowork.modle.entity.Candy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willhua on 2016/11/29.
 */

public class CandyData {

    private final String[] COLUMNS = new String[]{TomatoDbOpenHelper.CandyTable.KEY_ID, TomatoDbOpenHelper.CandyTable.KEY_TITLE,
            TomatoDbOpenHelper.CandyTable.KEY_DESCRIBE, TomatoDbOpenHelper.CandyTable.KEY_CURRENT_TOM,
            TomatoDbOpenHelper.CandyTable.KEY_CURRENT_TOM, TomatoDbOpenHelper.CandyTable.KEY_OBJECTIVE_TOM,
            TomatoDbOpenHelper.CandyTable.KEY_PRIORITY};

    private TomatoDbOpenHelper mDbOpenHelper;

    public CandyData(@NonNull TomatoDbOpenHelper helper) {
        mDbOpenHelper = helper;
    }

    public void addNewCandy(Candy candy) {
        ContentValues values = new ContentValues();
        values.put(TomatoDbOpenHelper.CandyTable.KEY_TITLE, candy.getTitle());
        values.put(TomatoDbOpenHelper.CandyTable.KEY_CURRENT_TOM, 0);
        values.put(TomatoDbOpenHelper.CandyTable.KEY_OBJECTIVE_TOM, candy.getObjectiveTomato());
        values.put(TomatoDbOpenHelper.CandyTable.KEY_TYPE, candy.getType());
        values.put(TomatoDbOpenHelper.CandyTable.KEY_STATE, TomatoDbOpenHelper.CandyTable.STATE_UNFINISHED);
        long id = mDbOpenHelper.getWritableDatabase().insert(TomatoDbOpenHelper.CandyTable.TABLE_NAME, null, values);
        candy.setID(id);
    }

    public void updateCandy(Candy candy) {
        ContentValues values = new ContentValues();
        values.put(TomatoDbOpenHelper.CandyTable.KEY_TITLE, candy.getTitle());
        values.put(TomatoDbOpenHelper.CandyTable.KEY_CURRENT_TOM, candy.getCurrentTomato());
        values.put(TomatoDbOpenHelper.CandyTable.KEY_OBJECTIVE_TOM, candy.getObjectiveTomato());
        values.put(TomatoDbOpenHelper.CandyTable.KEY_TYPE, candy.getType());
        if(candy.getObjectiveTomato() == candy.getCurrentTomato()){
            values.put(TomatoDbOpenHelper.CandyTable.KEY_STATE, TomatoDbOpenHelper.CandyTable.STATE_FINISHED);
        }else{
            values.put(TomatoDbOpenHelper.CandyTable.KEY_STATE, TomatoDbOpenHelper.CandyTable.STATE_UNFINISHED);
        }
        mDbOpenHelper.getWritableDatabase().update(TomatoDbOpenHelper.CandyTable.TABLE_NAME,
                values, TomatoDbOpenHelper.CandyTable.KEY_ID + "=?", new String[]{Long.toString(candy.getID())});
    }

    public void deleteCandy(long id){
        mDbOpenHelper.getWritableDatabase().delete(TomatoDbOpenHelper.CandyTable.TABLE_NAME,
                TomatoDbOpenHelper.CandyTable.KEY_ID + "=?", new String[]{Long.toString(id)});
    }

    public List<Candy> getAllUnfinishedCandy(){
        return queryCnadies(TomatoDbOpenHelper.CandyTable.STATE_UNFINISHED);
    }

    public List<Candy> getAllFinishedCandy(){
        return queryCnadies(TomatoDbOpenHelper.CandyTable.STATE_FINISHED);
    }

    private List<Candy> queryCnadies(String state){
        List candies = new ArrayList();
        Cursor cursor = mDbOpenHelper.getWritableDatabase().query(TomatoDbOpenHelper.CandyTable.TABLE_NAME, COLUMNS,
                TomatoDbOpenHelper.CandyTable.KEY_STATE + "=?", new String[]{state},
                null, null, null);
        if (cursor != null && cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Candy candy = new Candy(cursor.getString(cursor.getColumnIndex(TomatoDbOpenHelper.CandyTable.KEY_DESCRIBE)));
                candy.setID(cursor.getLong(cursor.getColumnIndex(TomatoDbOpenHelper.CandyTable.KEY_ID)));
                candy.setObjectiveTomato(cursor.getInt(cursor.getColumnIndex(TomatoDbOpenHelper.CandyTable.KEY_OBJECTIVE_TOM)));
                candy.setCurrentTomato(cursor.getInt(cursor.getColumnIndex(TomatoDbOpenHelper.CandyTable.KEY_CURRENT_TOM)));
                candy.setTitle(cursor.getString(cursor.getColumnIndex(TomatoDbOpenHelper.CandyTable.KEY_TITLE)));
                candy.setPriority(cursor.getInt(cursor.getColumnIndex(TomatoDbOpenHelper.CandyTable.KEY_PRIORITY)));
                candies.add(candy);
            }
        }
        if(cursor != null){
            cursor.close();
        }
        return  candies;
    }
}
