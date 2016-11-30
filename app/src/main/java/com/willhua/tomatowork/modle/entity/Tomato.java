package com.willhua.tomatowork.modle.entity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by willhua on 2016/11/13.
 */

public class Tomato {
    public interface TomatoEvent{
        void onSecond(int left);
        void onOver();
    }

    private static final int SECOND = 1000;
    private static Tomato sSingleton = new Tomato();
    private TomatoEvent mTomatoEvent;
    private int mTimeLong;
    private Timer mTimer;
    private int mLeftTime;
    private TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            if(mTomatoEvent != null){
                if(mLeftTime == 0){
                    mTomatoEvent.onOver();
                }else{
                    mTomatoEvent.onSecond(--mLeftTime);
                }
            }
        }
    };

    private Tomato(){
        mTimer = new Timer();
    }

    public static Tomato getInstance(){
        return sSingleton;
    }

    /**
     * set time on how long a tomao will last
     * @param minutes the unit is minute
     */
    public void setMinutes(int minutes){
        if(minutes < 1){
            minutes = 25;
        }
        minutes *= 60;
        mTimeLong = minutes;
    }

    /**
     * set the listener for candy_item.
     * @param tomatoEvent
     */
    public void setTomatoEvent(TomatoEvent tomatoEvent) {
        mTomatoEvent = tomatoEvent;
    }

    public void startTomato(){
        mLeftTime = mTimeLong;
        mTimer.purge();
        mTimer.schedule(mTimerTask, 0, SECOND);
    }

    public void cancleTomato(){
        mTimer.purge();
    }
}
