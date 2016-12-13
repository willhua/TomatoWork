package com.willhua.tomatowork.modle.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.willhua.tomatowork.core.CommandRunner;
import com.willhua.tomatowork.core.ICommand;
import com.willhua.tomatowork.modle.entity.Candy;
import com.willhua.tomatowork.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willhua on 2016/12/7.
 */

public class DbMaster {

    private static final String TAG = "DbMaster";

    private TomatoDbOpenHelper mHelper;
    private List<IObserver> mObservers;

    private static volatile DbMaster sMaster;
    private static Context sContext;


    private DbMaster() {
        mObservers = new ArrayList<>();
        mHelper = new TomatoDbOpenHelper(sContext);
        mHelper.test();
    }

    public static void init(Context context) {
        sContext = context.getApplicationContext();
    }

    static DbMaster getMaster() {
        if (sMaster == null) {
            synchronized (DbMaster.class) {
                if (sMaster == null) {
                    sMaster = new DbMaster();
                }
            }
        }
        return sMaster;
    }

    void registerObserver(IObserver observer){
        if(observer != null){
        mObservers.add(observer);
        }
    }

    void unregisterOberver(IObserver observer){
        if(observer != null){
            mObservers.remove(observer);
        }
    }

    private void notifyObservers(String table){
        for(IObserver observer : mObservers){
            LogUtil.d(TAG, "notify:" + observer);
            observer.onDataChanged(table);
        }
    }


    int delete(String table, String whereClause, String[] whereArgs) {
        int cnt =  mHelper.getWritableDatabase().delete(table, whereClause, whereArgs);
        notifyObservers(table);
        return cnt;
    }

    long insert(String table, String nullColumnHack, ContentValues values) {
        long id = mHelper.getWritableDatabase().insert(table, nullColumnHack, values);
        notifyObservers(table);
        return id;
    }

    Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return mHelper.getReadableDatabase().query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    int update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        int cnt = mHelper.getWritableDatabase().update(table, values, whereClause, whereArgs);
        notifyObservers(table);
        return cnt;
    }
}
