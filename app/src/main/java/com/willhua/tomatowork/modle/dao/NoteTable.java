package com.willhua.tomatowork.modle.dao;

/**
 * Created by willhua on 2016-11-17.
 */

public class NoteTable {
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
