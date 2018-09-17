package com.github.fcopardo.easyrest.desktop

import com.github.fcopardo.easyrest.common.BaseJVMRestWorker
import com.github.fcopardo.easyrest.common.EasyRest
import java.io.File
import java.security.NoSuchAlgorithmException


class DesktopRestWorker<T, X, Z> : BaseJVMRestWorker<T, X, DesktopPlatform> {

    constructor(platform : DesktopPlatform, responseClass: Class<T>, requestClass: Class<X>):super(platform, responseClass, requestClass){
        EasyRest.build(platform)
    }

    fun getCachedFileName(): String {

        if (cachedFile.isEmpty() || cachedFile=="") {

            var queryKey = ""
            try {
                queryKey = getPlatform()!!.getHashOne(getURI().authority + getURI().path.replace("/", "_") + getURI().query)
            } catch (e: NoSuchAlgorithmException) {
                queryKey = getURI().authority + getURI().path.replace("/", "_") + getURI().query
                e.printStackTrace()
            }

            return (System.getProperty("user.dir") + File.separator + "EasyRest" + File.separator
                    + jsonResponseEntityClass.simpleName
                    + queryKey)
        }
        System.out.println("CACHE: $cachedFile")
        return cachedFile
    }


}