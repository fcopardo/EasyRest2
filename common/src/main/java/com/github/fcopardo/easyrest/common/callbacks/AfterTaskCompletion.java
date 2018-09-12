package com.github.fcopardo.easyrest.common.callbacks;

/**
 * Implement this interface in the summoner class to receive and process the results of the rest call asynchronously.
 *
 * Created by Fco Pardo on 8/23/14.
 */
public interface AfterTaskCompletion<T> {

    /**
     * Implement the desired behavior after receiving the rest call result.
     * @param result
     */
    void onTaskCompleted(T result);
}
