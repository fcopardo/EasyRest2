package com.github.fcopardo.easyrest.android

import com.github.fcopardo.easyrest.common.BaseJVMRestWorker
import com.github.fcopardo.easyrest.common.EasyRest
import java.io.File
import java.security.NoSuchAlgorithmException
import android.util.Log
import com.github.fcopardo.easyrest.common.PoolExecutor
import java.io.IOException
import java.util.*


class AndroidRestWorker<T, X, Z> : BaseJVMRestWorker<T, X, AndroidPlatform> {

    constructor(platform: AndroidPlatform, responseClass: Class<T>, requestClass: Class<X>):super(platform, responseClass, requestClass){
       EasyRest.build(getPlatform())
    }

    override fun showMessage(title: String, message: String) {
        Log.e(title, message)
    }

    override fun getCachedFileName(): String {

        if (cachedFile.isEmpty() || cachedFile.trim() == "") {

            var queryKey = ""
            try {
                var fileName = getURI().authority + getURI().path.replace("/", "_") + getURI().query
                queryKey = getPlatform()?.getHashOne(fileName)!!
            } catch (e: NoSuchAlgorithmException) {
                queryKey = getURI().authority + getURI().path.replace("/", "_") + getURI().query
                e.printStackTrace()
            }

            return (getPlatform()!!.fullPath
                    + jsonResponseEntityClass.simpleName
                    + queryKey)
        }
        if (EasyRest.get().isDebugMode) showMessage("EasyRest - Cache", "CACHE: $cachedFile")
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