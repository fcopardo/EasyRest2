package com.github.fcopardo.easyrest.desktop

import com.github.fcopardo.easyrest.common.BaseJVMRestWorker
import com.github.fcopardo.easyrest.common.EasyRest
import java.io.File
import java.security.NoSuchAlgorithmException
import java.io.IOException


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

    private fun createSolidCache() {

        EasyRest.get()!!.cacheRequest(getCachedFileName(), getJsonResponseEntity())
        Thread(Runnable {
            val mapper = ObjectMapper()

            try {
                val dir = File(getPlatform()?.basePath + File.separator + "EasyRest")
                dir.mkdir()
                val f = File(getCachedFileName())
                mapper.writeValue(f, getJsonResponseEntity())
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }).start()

    }


}