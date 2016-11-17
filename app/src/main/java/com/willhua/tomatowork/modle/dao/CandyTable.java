package com.willhua.tomatowork.modle.dao;

/**
 * Created by willhua on 2016-11-17.
 */

public class CandyTable {
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