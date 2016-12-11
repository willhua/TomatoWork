package com.willhua.tomatowork.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by willhua on 2016/11/13.
 */

public class Tomato {
    public interface TomatoEvent {
        void onSecond(int left);

        void onOver();
    }

    private static final int SECOND = 1000;
    private static Tomato sSingleton = new Tomato();
    private List<TomatoEvent> mTomatoEvents;
    private int mTimeLong;
    private Timer mTimer;
    private int mLeftTime;
    private boolean mIsDuringTomato;
    private TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            if (mLeftTime == 0) {
                for (TomatoEvent event : mTomatoEvents) {
                    mLeftTime = mTimeLong;
                    cancleTomato();
                    event.onOver();
                }
            } else {
                int second = --mLeftTime;
                for (TomatoEvent event : mTomatoEvents) {
                    event.onSecond(second);
                }
            }
        }
    };

    private Tomato() {
        mTimer = new Timer();
        mTomatoEvents = new ArrayList<>(4);
    }

    public static Tomato getInstance() {
        return sSingleton;
    }

    /**
     * set time on how long a tomao will last
     *
     * @param minutes the unit is minute
     */
    public void setMinutes(int minutes) {
        if (minutes < 1) {
            minutes = 25;
        }
        minutes *= 60;
        mTimeLong = 10;
    }

    /**
     * register the listener for candy_item.
     *
     * @param tomatoEvent
     */
    public void registerTomatoEvent(TomatoEvent tomatoEvent) {
        mTomatoEvents.add(tomatoEvent);
    }

    public void unregisterTomatoEvent(TomatoEvent tomatoEvent) {
        mTomatoEvents.remove(tomatoEvent);
    }

    public void startTomato() {
        mLeftTime = mTimeLong;
        mTimer.purge();
        mTimer.schedule(mTimerTask, 0, SECOND);
        mIsDuringTomato = true;
    }

    public void cancleTomato() {
        mTimer.purge();
        mIsDuringTomato = false;
    }

    public boolean isDuringTomato() {
        return mIsDuringTomato;
    }
}
