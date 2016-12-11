package com.willhua.tomatowork.modle.db;

/**
 * Created by willhua on 2016/12/7.
 */

public interface CandyTable {
    String STATE_FINISHED = "1";
    String STATE_UNFINISHED = "0";
    String TABLE_NAME = "candytable";
    String KEY_ID = "_id";
    String KEY_TITLE = "title";
    String KEY_DESCRIBE = "describe";
    String KEY_OBJECTIVE_TOM = "objective";
    String KEY_CURRENT_TOM = "current";
    String KEY_TYPE = "type";
    String KEY_PRIORITY = "priority";
    String KEY_STATE = "state";
    String KEY_MODIFY_TIME = "modifytime";
}
