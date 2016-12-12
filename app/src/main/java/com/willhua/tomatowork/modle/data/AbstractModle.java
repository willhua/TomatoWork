package com.willhua.tomatowork.modle.data;

import com.willhua.tomatowork.modle.data.idata.IModle;

/**
 * Created by willhua on 2016/12/13.
 */

public class AbstractModle implements IModle {
    private IObserver mObserver;


    @Override
    public void unregisterObserver() {
        DbMaster.getMaster().unregisterOberver(mObserver);
    }

    @Override
    public void registerOberver(IObserver observer) {
        unregisterObserver();
        mObserver = observer;
        DbMaster.getMaster().registerObserver(mObserver);
    }
}
