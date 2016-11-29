package com.willhua.tomatowork.modle.dao;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.willhua.tomatowork.modle.entity.Candy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willhua on 2016/11/29.
 */

public class CandyData {

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

    public List<Candy> getAllUnfinishedCandy(){
        List candies = new ArrayList();
        return  candies;
    }
}
