package com.willhua.tomatowork.core;

import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;

import com.willhua.tomatowork.utils.LogUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by willhua on 2016/12/7.
 */

public class CommandRunner implements ICommandRunner {

    private static final String TAG = "CommandRunner";
    private static final int MSG_QUERY_FINISH = 0;
    private ExecutorService mExecutorService;
    private Handler mHandler;

    private volatile static CommandRunner sRunner;

    public static ICommandRunner getRunner(){
        if(sRunner == null){
            synchronized (CommandRunner.class){
                if(sRunner == null){
                    sRunner = new CommandRunner();
                }
            }
        }
        return sRunner;
    }

    private CommandRunner(){
        mExecutorService = Executors.newSingleThreadScheduledExecutor();
        mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
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

    @Override
    public void release() {
        mExecutorService.shutdown();
        sRunner = null;
    }
}
