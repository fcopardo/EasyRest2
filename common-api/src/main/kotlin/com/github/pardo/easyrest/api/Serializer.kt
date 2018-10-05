package com.github.pardo.easyrest.api

import kotlin.reflect.KClass

/**
 * Placeholder for Json serialization
 */
interface Serializer {
    fun <T> serialize(data : T, type : KClass<*>) : String
    fun <T> deserialize(json: String, type : KClass<*>) : T
}