package com.willhua.tomatowork.modle.data;

import com.willhua.tomatowork.modle.entity.Candy;

import java.util.List;

/**
 * Created by willhua on 2016/12/12.
 */

public interface IObserver {
    int TYPE_INSERT = 0;
    int TYPE_UPDATE = 1;
    int TYPE_DELETE = 2;
    void onDataChanged(List<Candy> candies, int type);
}
