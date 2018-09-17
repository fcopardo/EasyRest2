package com.github.fcopardo.easyrest.desktop

import com.github.fcopardo.easyrest.common.Platform
import java.io.File
import java.nio.file.Files.delete
import java.io.File.separator
import java.io.FileFilter
import java.util.*
import java.util.concurrent.FutureTask
import java.util.Arrays.asList

class DesktopPlatform : Platform {
    override fun getFullPath(): String {
        return System.getProperty("user.dir") + File.separator + "EasyRest" + File.separator
    }

    override fun getBasePath(): String {
        return System.getProperty("user.dir")
    }

    override fun deleteCache() {
        class Task : Runnable {

            var workingDir = System.getProperty("user.dir")

            override fun run() {
                val f = File(workingDir + separator + "EasyRest")
                if (f.exists()) {
                    for (file in f.listFiles()) {
                        file.delete()
                    }
                }
            }
        }

        val myTask = Task()
        val futureTask = FutureTask(myTask, null)
        futureTask.run()
    }

    override fun deleteCache(classes: MutableList<Class<Any>>?, maximumTime: Long) {
        class Task : Runnable {

            var workingDir = System.getProperty("user.dir")
            var classes: List<Class<*>> = ArrayList()
            var maximumTime: Long = 0

            override fun run() {

                val f = File(workingDir + File.separator + "EasyRest")

                val filter = FileFilter { pathname ->
                    for (aClass in classes!!) {
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
        val futureTask = FutureTask(myTask, null)
        futureTask.run()
    }

    override fun checkConnectivity(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}