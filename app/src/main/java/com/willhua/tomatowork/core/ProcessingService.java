package com.willhua.tomatowork.core;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import com.willhua.tomatowork.utils.LogUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by willhua on 2016/12/7.
 */

public class ProcessingService extends Service implements ICommandRunner {

    public class LocalCommandRunner extends Binder {
        public ICommandRunner getCommandRunner() {
            return ProcessingService.this;
        }
    }

    private static final String TAG = "ProcessingService";
    private static final int MSG_QUERY_FINISH = 0;
    private Binder mBinder;
    private ExecutorService mExecutorService;
    private Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mBinder = new LocalCommandRunner();
        mExecutorService = Executors.newSingleThreadScheduledExecutor();
        mHandler = new Handler(getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_QUERY_FINISH:
                        try {
                            ICommand command = (ICommand) msg.obj;
                            command.updateUI();
                        } catch (Exception e) {
                            LogUtil.d(TAG, "MSG_QUERY_FINISH Exception:" + e.getMessage());
                            e.printStackTrace();
                        } finally {

                        }
                    default:
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.d(TAG, "onBind ");
        return mBinder;
    }

    @Override
    public void runCommand(final ICommand command) {
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                command.execute();
                Message msg = Message.obtain();
                msg.what = MSG_QUERY_FINISH;
                msg.obj = command;
                mHandler.sendMessage(msg);
            }
        });
    }
}
