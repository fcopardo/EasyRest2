package com.github.fcopardo.easyrest.common.callbacks

/**
 * Implement this interface in the summoner class to perform any fallback operations when the rest call fails.
 * Created by Fco Pardo on 8/24/14.
 */
interface AfterClientTaskFailure {
    fun onClientTaskFailed(error: Int)
}
