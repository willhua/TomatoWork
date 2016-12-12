package com.willhua.tomatowork.modle.data.idata;

import com.willhua.tomatowork.modle.data.IObserver;

/**
 * Created by willhua on 2016/12/13.
 */

public interface IModle {
    void unregisterObserver();
    void registerOberver(IObserver observer);
}
