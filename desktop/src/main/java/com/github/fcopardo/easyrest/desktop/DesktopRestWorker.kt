package com.github.fcopardo.easyrest.desktop

import com.github.fcopardo.easyrest.common.BaseJVMRestWorker

class DesktopRestWorker<T, X, Z> : BaseJVMRestWorker<T, X, DesktopPlatform> {

    constructor(platform : DesktopPlatform, responseClass: Class<T>, requestClass: Class<X>):super(platform, responseClass, requestClass){

    }
}