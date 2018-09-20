package com.github.fcopardo.easyrest.desktop

import com.github.fcopardo.easyrest.common.BaseJVMRestWorker
import com.github.fcopardo.easyrest.common.EasyRest
import com.github.fcopardo.easyrest.common.PoolExecutor
import java.io.File
import java.security.NoSuchAlgorithmException
import java.io.IOException
import java.util.*


class DesktopRestWorker<T, X, Z> : BaseJVMRestWorker<T, X, DesktopPlatform> {

    constructor(platform : DesktopPlatform, responseClass: Class<T>, requestClass: Class<X>)
            : super(platform, responseClass, requestClass){
        EasyRest.build(platform)
    }

    override fun showMessage(title: String, message: String) {
        System.out.println("$title : $message")
    }

    override fun getCachedFileName(): String {

        if (cachedFile.isEmpty() || cachedFile=="") {

            var queryKey = ""
            try {
                queryKey = getPlatform()!!.getHashOne(getURI().authority + getURI().path.replace("/", "_") + getURI().query)
            } catch (e: NoSuchAlgorithmException) {
                queryKey = getURI().authority + getURI().path.replace("/", "_") + getURI().query
                e.printStackTrace()
            }

            return (getPlatform()!!.fullPath
                    + jsonResponseEntityClass.simpleName
                    + queryKey)
        }
        System.out.println("CACHE: $cachedFile")
        return cachedFile
    }

    private fun createSolidCache() {

        EasyRest.get().cacheRequest(getCachedFileName(), getJsonResponseEntity())
        class Task : Runnable {

            override fun run() {

                try {
                    val random = Random()
                    val x = random.nextInt()
                    showMessage("EasyRest-Cache", "Starting serialization $x")
                    val dir = File(getPlatform()!!.basePath + File.separator + "EasyRest")
                    dir.mkdir()
                    val f = File(getCachedFileName())
                    mapper!!.writeValue(f, getJsonResponseEntity()!!)
                    showMessage("EasyRest-Cache", "Finished serialization $x")
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }

        val task = Task()
        PoolExecutor.get().getExecutor(true).execute(task)
    }


}