package com.willhua.tomatowork.modle.dao;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.willhua.tomatowork.utils.LogUtil;

/**
 * Created by willhua on 2016/11/25.
 */

public class TomatoDbOpenHelper extends SQLiteOpenHelper {

    public static class NoteTable {
        public static final String TABLE_NAME = "notetable";
        public static final String KEY_ID = "_id";
        public static final String KEY_DESCRIBE = "describe";
        public static final String KEY_TYPE = "type";
        public static final String KEY_PRIORITY = "priority";
        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS '" + TABLE_NAME + "' ("
                + KEY_ID + " integer PRIMARY KEY autoincrement ,"
                + KEY_DESCRIBE + ", "
                + KEY_TYPE + " integer, "
                + KEY_PRIORITY + " integer )";
    }

    public static class CandyTable {
        public static final String STATE_FINISHED = "1";
        public static final String STATE_UNFINISHED = "0";
        public static final String TABLE_NAME = "candytable";
        public static final String KEY_ID = "_id";
        public static final String KEY_TITLE = "title";
        public static final String KEY_DESCRIBE = "describe";
        public static final String KEY_OBJECTIVE_TOM = "objective";
        public static final String KEY_CURRENT_TOM = "current";
        public static final String KEY_TYPE = "type";
        public static final String KEY_PRIORITY = "priority";
        public static final String KEY_STATE = "state";
        public static final String CREATE_TABLE = "CREATE TABLE if not exists '" + TABLE_NAME + "' ("
                + KEY_ID + " integer PRIMARY KEY autoincrement, "
                + KEY_TITLE + ", "
                + KEY_DESCRIBE + ", "
                + KEY_OBJECTIVE_TOM + " integer , "
                + KEY_CURRENT_TOM + " integer, "
                + KEY_TYPE + " integer, "
                + KEY_PRIORITY + " integer, "
                + KEY_STATE + ")";
    }

    private static final String DATABASE_NAME = "willhua_tomatowork.db";
    private static final int DATABASE_VERSION = 1;

    public TomatoDbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CandyTable.CREATE_TABLE);
        db.execSQL(NoteTable.CREATE_TABLE);
        LogUtil.d("openhelper", "oncreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
