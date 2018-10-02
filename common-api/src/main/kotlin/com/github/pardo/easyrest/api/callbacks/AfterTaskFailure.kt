package com.github.fcopardo.easyrest.api.callbacks

/**
 * Implement this interface in the summoner class to perform any fallback operations when the rest call fails.
 * Created by Fco Pardo on 8/24/14.
 */
interface AfterTaskFailure {
    fun <L : Exception> onTaskFailed(e: L)
}
