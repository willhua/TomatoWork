package com.willhua.tomatowork.core;

import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by willhua on 2016/12/7.
 */

public interface ICommand {

    /**
     * this method will be run on background thread to finish sqlite qurey.
     * So, no UI action can be done during this method
     */
    void execute();

    /**
     * the method will be run on the main thread to update UI by using handler
     */
    void updateUI();
}
