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

public class TomatoProvider extends ContentProvider {
    public static final String AUTHORITY = "content://com.willhua.tomatowork.databaseeprovider";

    public static class NoteTable {
        public static final Uri CONTENT_URI = Uri.parse(TomatoProvider.AUTHORITY + "/note");

        public static final String TABLE_NAME = "notetable";
        public static final String KEY_ID = "_id";
        public static final String KEY_DESCRIBE = "describe";
        public static final String KEY_TYPE = "type";
        public static final String KEY_PRIORITY = "priority";
        public static final String CREATE_TABLE = "CREATE TABLE if not exists " + TABLE_NAME + " ("
                + KEY_ID + " integer PRIMARY KEY autoincrement ,"
                + KEY_DESCRIBE + ", "
                + KEY_TYPE + " integer, "
                + KEY_PRIORITY + " integer, "
                + " UNIQUE (" + TABLE_NAME + "));";
    }

    public static class CandyTable {
        public static final Uri CONTENT_URI = Uri.parse(TomatoProvider.AUTHORITY + "/candy");
        public static final String TABLE_NAME = "candytable";
        public static final String KEY_ID = "_id";
        public static final String KEY_TITLE = "title";
        public static final String KEY_DESCRIBE = "describe";
        public static final String KEY_OBJECTIVE_TOM = "objective";
        public static final String KEY_CURRENT_TOM = "current";
        public static final String KEY_TYPE = "type";
        public static final String KEY_PRIORITY = "priority";
        public static final String CREATE_TABLE = "CREATE TABLE if not exists " + TABLE_NAME + " ("
                + KEY_ID + " integer PRIMARY KEY autoincrement, "
                + KEY_TITLE + ", "
                + KEY_DESCRIBE + ", "
                + KEY_OBJECTIVE_TOM + " integer , "
                + KEY_CURRENT_TOM + " integer, "
                + KEY_TYPE + " integer, "
                + KEY_PRIORITY + " integer, "
                + " UNIQUE (" + TABLE_NAME + "));";
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }


    private static class TomatoDbOpenHelper extends SQLiteOpenHelper{
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
}
