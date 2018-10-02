package com.github.fcopardo.easyrest.api

import com.github.fcopardo.easyrest.api.callbacks.*

/**
 * Redefines the bounded type so JVMPlatform is used in the JVM instead of JVMPlatform
 */
interface RestWorker<T, X, out Platform> : BaseRestWorker<T, X, Platform>{


}