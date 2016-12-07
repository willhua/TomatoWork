package com.willhua.tomatowork.modle.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.willhua.tomatowork.utils.LogUtil;

/**
 * Created by willhua on 2016/11/25.
 */

class TomatoDbOpenHelper extends SQLiteOpenHelper {


    private static final String CREATE_NOTE_TABLE = "CREATE TABLE IF NOT EXISTS '" + NoteTable.TABLE_NAME + "' ("
            + NoteTable.KEY_ID + " integer PRIMARY KEY autoincrement ,"
            + NoteTable.KEY_DESCRIBE + ", "
            + NoteTable.KEY_TYPE + " integer, "
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

    private static final String DATABASE_NAME = "willhua_tomatowork.db";
    private static final int DATABASE_VERSION = 1;

    public TomatoDbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CANDY_TABLE);
        db.execSQL(CREATE_NOTE_TABLE);
        LogUtil.d("openhelper", "oncreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
