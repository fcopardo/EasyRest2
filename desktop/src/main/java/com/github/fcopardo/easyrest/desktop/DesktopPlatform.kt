package com.github.fcopardo.easyrest.desktop

import com.github.fcopardo.easyrest.common.JVMPlatform
import java.io.File
import java.io.File.separator
import java.io.FileFilter
import java.util.concurrent.FutureTask

class DesktopPlatform : JVMPlatform {

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

    override fun deleteCache(classes: List<Class<*>>, maximumTime: Long) {
        class Task : Runnable {

            var workingDir = System.getProperty("user.dir")

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