package com.github.fcopardo.easyrest.common

import com.github.pardo.easyrest.api.KotlinSerializer
import kotlin.reflect.KClass

interface KotlinJVMSerializer{
    fun <T> deserialize(json: String, type : KClass<*>) : T
    fun <T> deserialize(json: String, type : Class<T>) : T
    fun <T> serialize(data : T, type : KClass<*>) : String
    fun <T> serialize(data : T, type : Class<T>) : String
}