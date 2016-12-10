package com.willhua.tomatowork.modle.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by willhua on 2016/12/7.
 */

public class DbMaster {

    private TomatoDbOpenHelper mHelper;

    private static volatile DbMaster sMaster;
    private static Context sContext;


    private DbMaster(){
        mHelper = new TomatoDbOpenHelper(sContext);
/*        mHelper.testInsertCandy();
        mHelper.testInsertNote();
        mHelper.testInsertTomato();*/
    }

    public static void init(Context context){
        sContext = context.getApplicationContext();
    }

    static DbMaster getMaster(){
        if(sMaster == null){
            synchronized (DbMaster.class){
                if(sMaster == null){
                    sMaster = new DbMaster();
                }
            }
        }
        return sMaster;
    }

    SQLiteDatabase getWritableDatabase(){
        return mHelper.getWritableDatabase();
    }

    SQLiteDatabase getReadableDatabase(){
        return mHelper.getReadableDatabase();
    }
}
