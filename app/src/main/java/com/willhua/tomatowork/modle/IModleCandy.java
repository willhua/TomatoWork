package com.willhua.tomatowork.modle;

import com.willhua.tomatowork.modle.entity.Candy;

import java.util.List;

/**
 * Created by willhua on 2016-11-17.
 */

public interface IModleCandy {
    List<Candy> getGrowingCandy();
    List<Candy> getFinishedCandy();
    void modifyCandy(Candy candy);
    void deleteCandy(Candy candy);
    void addCandy(Candy candy);
}
