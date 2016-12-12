package com.willhua.tomatowork.modle.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.willhua.tomatowork.modle.entity.Candy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willhua on 2016/12/7.
 */

public class DbMaster {

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

    private void notifyObservers(List<Candy> candies, int type){
        for(IObserver observer : mObservers){
            observer.onDataChanged(candies, type);
        }
    }



    int delete(String table, String whereClause, String[] whereArgs) {
        return mHelper.getWritableDatabase().delete(table, whereClause, whereArgs);
    }

    long insert(String table, String nullColumnHack, ContentValues values) {
        return mHelper.getWritableDatabase().insert(table, nullColumnHack, values);
    }

    Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return mHelper.getReadableDatabase().query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    int update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        return mHelper.getWritableDatabase().update(table, values, whereClause, whereArgs);
    }
}
