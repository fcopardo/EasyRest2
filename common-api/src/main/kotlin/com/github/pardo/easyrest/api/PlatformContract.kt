package com.github.fcopardo.easyrest.api


interface PlatformContract {

    fun deleteCache()
    fun checkConnectivity(): Boolean
    fun getBasePath(): String
    fun getFullPath(): String

}