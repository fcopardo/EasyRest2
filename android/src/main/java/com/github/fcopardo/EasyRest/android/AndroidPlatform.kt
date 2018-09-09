package com.github.fcopardo.easyrest.android

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.github.fcopardo.easyrest.common.Platform
import java.io.File
import java.io.FileFilter
import java.util.*

class AndroidPlatform : Platform {

    var application : Application? = null

    fun setApplication(app:Application) : AndroidPlatform{
        application = app
        return this
    }

    /**
     * Deletes the EasyRest cache.
     * @param context a valid application context.
     */
    override fun deleteCache() {
        class Task : Runnable {
            var context: Context? = null

            override fun run() {
                val f = File(context!!.cacheDir.absolutePath + File.separator + "EasyRest")
                if (f.exists()) {
                    for (file in f.listFiles()) {
                        file.delete()
                    }
                }
                context = null
            }
        }

        val myTask = Task()
        myTask.context = application?.applicationContext
        val thread = Thread(myTask)
        thread.start()
    }

    /**
     * Deletes the EasyRest cache of the specified types of answer, and older than @maximumTime
     * @param classes the response types to be deleted.
     * @param maximumTime The maximum caching time.
     */
    override fun deleteCache(classes: List<Class<*>>, maximumTime: Long) {

        class Task : Runnable {

            var context: Context? = null
            var classes: List<Class<*>> = ArrayList()
            var maximumTime: Long = 0


            override fun run() {
                val f = File(context?.cacheDir?.absolutePath + File.separator + "EasyRest")

                val filter = FileFilter { pathname ->
                    for (aClass in classes) {
                        if (pathname.name.contains(aClass.simpleName) && pathname.lastModified() > maximumTime) {
                            return@FileFilter true
                        }
                    }
                    false
                }

                if (f.exists()) {
                    val files = f.listFiles(filter).toMutableList()
                    for (file : File in files) {
                        file.delete()
                    }
                }
            }
        }

        val myTask = Task()
        myTask.classes = classes
        myTask.context = application?.applicationContext
        myTask.maximumTime = maximumTime
        val thread = Thread(myTask)
        thread.start()
    }

    override fun checkConnectivity(): Boolean {
        var context: Context? = application.applicationContext
        val cm = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        return isConnected
    }

}