package com.github.fcopardo.easyrest.android

import android.content.Context
import com.github.fcopardo.easyrest.common.BaseJVMRestWorker
import com.github.fcopardo.easyrest.common.EasyRest
import java.io.File
import java.security.NoSuchAlgorithmException


class AndroidRestWorker<T, X, Z> : BaseJVMRestWorker<T, X, AndroidPlatform> {

    constructor(platform: AndroidPlatform, responseClass: Class<T>, requestClass: Class<X>):super(platform, responseClass, requestClass){
       EasyRest.build(getPlatform())
    }

    fun getCachedFileName(): String {

        var context = getPlatform()?.getApplication()?.applicationContext

        if (cachedFile.isEmpty() || cachedFile.trim() == "") {

            var queryKey = ""
            try {
                var fileName = getURI().authority + getURI().path.replace("/", "_") + getURI().query
                queryKey = getPlatform()?.getHashOne(fileName)!!
            } catch (e: NoSuchAlgorithmException) {
                queryKey = getURI().authority + getURI().path.replace("/", "_") + getURI().query
                e.printStackTrace()
            }

            return (context?.cacheDir?.absolutePath + File.separator + "EasyRest" + File.separator
                    + jsonResponseEntityClass.simpleName
                    + queryKey)
        }
        if (EasyRest.get().isDebugMode) System.out.println("CACHE: $cachedFile")
        return cachedFile
    }
}