package com.github.fcopardo.easyrest.common.callbacks


import com.github.fcopardo.easyrest.common.HttpStatus

/**
 * Created by FcoPardo on 6/7/15.
 */
interface CommonTasks {
    fun performCommonTask(result: Boolean, statusCode: Int)
}
