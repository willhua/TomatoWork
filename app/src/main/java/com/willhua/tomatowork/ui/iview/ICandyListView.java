package com.willhua.tomatowork.ui.iview;

import com.willhua.tomatowork.modle.entity.Candy;

import java.util.List;

/**
 * Created by willhua on 2016/12/7.
 */

public interface ICandyListView {
    void onFinishedCandyQueried(List<Candy> candies);
    void onUnfinishedCandyQueried(List<Candy> candies);
}
