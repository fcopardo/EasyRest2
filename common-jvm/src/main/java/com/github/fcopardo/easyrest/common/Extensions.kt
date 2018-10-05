package com.github.fcopardo.easyrest.common

import kotlin.reflect.KClass

class Extensions {

    fun <X> Class<X>.toKClass() : KClass<*>{
        return Class.forName(this.canonicalName).kotlin
    }

}