package com.github.fcopardo.easyrest.common

import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class PoolExecutor private constructor(){

    companion object {
        private var INSTANCE = PoolExecutor()

        fun get() : PoolExecutor{
            return INSTANCE
        }
    }

    private val workQueue : BlockingQueue<Runnable> = LinkedBlockingQueue()
    private var globalExecutor: ThreadPoolExecutor? = null
    private var cachingExecutor: ThreadPoolExecutor? = null

    fun getExecutor(caching: Boolean): ThreadPoolExecutor {
        val executor: ThreadPoolExecutor
        if (caching) {
            if (cachingExecutor == null) {
                cachingExecutor = ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, workQueue)
            }
            executor = cachingExecutor!!
        } else {
            if (globalExecutor == null) {
                var processors = Runtime.getRuntime().availableProcessors()
                if (processors >= 2) processors = processors / 2
                globalExecutor = ThreadPoolExecutor(processors, processors, 1, TimeUnit.SECONDS, workQueue)
            }
            executor = globalExecutor!!
        }
        return executor
    }
}