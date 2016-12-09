package com.willhua.tomatowork.modle.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.willhua.tomatowork.utils.LogUtil;

import java.util.Random;

/**
 * Created by willhua on 2016/11/25.
 */

class TomatoDbOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = "TomatoDbOpenHelper";

    private static final String CREATE_NOTE_TABLE = "CREATE TABLE IF NOT EXISTS '" + NoteTable.TABLE_NAME + "' ("
            + NoteTable.KEY_ID + " integer PRIMARY KEY autoincrement ,"
            + NoteTable.KEY_DESCRIBE + ", "
            + NoteTable.KEY_TYPE + " integer, "
            + NoteTable.KEY_TITLE + ", "
            + NoteTable.KEY_PRIORITY + " integer )";


    private static final String CREATE_CANDY_TABLE = "CREATE TABLE if not exists '" + CandyTable.TABLE_NAME + "' ("
            + CandyTable.KEY_ID + " integer PRIMARY KEY autoincrement, "
            + CandyTable.KEY_TITLE + ", "
            + CandyTable.KEY_DESCRIBE + ", "
            + CandyTable.KEY_OBJECTIVE_TOM + " integer , "
            + CandyTable.KEY_CURRENT_TOM + " integer, "
            + CandyTable.KEY_TYPE + " integer, "
            + CandyTable.KEY_PRIORITY + " integer, "
            + CandyTable.KEY_STATE + ")";

    private static final String CREATE_TOMATO_TABLE = "CREATE TABLE if not exists '" + TomatoTable.TABLE_NAME + "' ("
            + TomatoTable.KEY_ID + " integer PRIMARY KEY autoincrement, "
            + TomatoTable.KEY_YEAR + " integer , "
            + TomatoTable.KEY_MONTH + " integer , "
            + TomatoTable.KEY_DAY + " integer , "
            + TomatoTable.KEY_HOUR + " integer)";

    private static final String DATABASE_NAME = "willhua_tomatowork.db";
    private static final int DATABASE_VERSION = 1;

    public TomatoDbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CANDY_TABLE);
        db.execSQL(CREATE_NOTE_TABLE);
        db.execSQL(CREATE_TOMATO_TABLE);
        if(false){
            testInsertCandy();
            testInsertNote();
            testInsertTomato();
        }
        LogUtil.d(TAG, "oncreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void testInsertCandy(){
        Random random = new Random();
        ContentValues values;
        for(int i = 0; i < 20; i++){
            values = new ContentValues();
            values.put(CandyTable.KEY_TITLE, "CandyTest" + i);
            values.put(CandyTable.KEY_DESCRIBE, "CandyTest KEY_DESCRIBE" + i);
            int num = random.nextInt(20);
            values.put(CandyTable.KEY_OBJECTIVE_TOM, num);
            values.put(CandyTable.KEY_CURRENT_TOM, num / 2);
            long id = getWritableDatabase().insert(CandyTable.TABLE_NAME, null, values);
            LogUtil.d(TAG, "INSERT CANDY " + id);

        }
    }

    private void testInsertNote(){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 15; i++){
            ContentValues values = new ContentValues();
            values.put(NoteTable.KEY_TITLE, "NoteTest" + i);
            builder.delete(0, builder.length());
            builder.append("describe:");
            for(int j = 0; j < 3; j++){
                builder.append("time ");
                builder.append(System.currentTimeMillis());
            }
            values.put(NoteTable.KEY_DESCRIBE, builder.toString());
            long id = getWritableDatabase().insert(NoteTable.TABLE_NAME, null, values);
            LogUtil.d(TAG, "INSERT NOTE " + id);
        }
    }

    private void testInsertTomato() {
        Random random = new Random();
        for (int m = 9; m < 13; m++) {
            for (int d = 1; d < 31; d++) {
                for (int h = 8; h < 24; h++) {
                    if(random.nextInt(2) == 1){ //not add for erery hour
                        ContentValues values = new ContentValues();
                        values.put(TomatoTable.KEY_YEAR, 2016);
                        values.put(TomatoTable.KEY_MONTH, m);
                        values.put(TomatoTable.KEY_DAY, d);
                        values.put(TomatoTable.KEY_HOUR, h);
                        long id = getWritableDatabase().insert(TomatoTable.TABLE_NAME, null, values);
                        LogUtil.d(TAG, "INSERT tomato " + id);

                    }
                }
            }
        }
    }
}
