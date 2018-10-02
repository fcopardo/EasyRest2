package com.github.fcopardo.easyrest.api

interface PlatformContract<T> {

    fun deleteCache()
    fun deleteCache(classes: List<T>, maximumTime: Long)
    fun checkConnectivity(): Boolean
    fun getBasePath(): String
    fun getFullPath(): String
    fun getHashOne(password: String): String

}