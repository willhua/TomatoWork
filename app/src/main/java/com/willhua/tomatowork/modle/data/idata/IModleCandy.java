package com.willhua.tomatowork.modle.data.idata;

import com.willhua.tomatowork.modle.entity.Candy;

import java.util.List;

/**
 * Created by willhua on 2016-11-17.
 */

public interface IModleCandy extends IModle {
    List<Candy> getAllFinishedCandy();
    List<Candy> getAllUnfinishedCandy();
    void updateCandy(Candy candy);
    void deleteCandy(long id);
    void addCandy(Candy candy);
}
