package com.willhua.tomatowork.core;

/**
 * Created by willhua on 2016/12/7.
 */

public interface ICommandRunner {
    void runCommand(ICommand command);
    void release();
}
