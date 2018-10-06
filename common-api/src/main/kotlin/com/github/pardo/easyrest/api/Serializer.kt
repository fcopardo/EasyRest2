package com.github.pardo.easyrest.api

import kotlin.reflect.KClass

/**
 * Placeholder for Json serialization
 */
interface Serializer {
    fun <T : Any> serializeKotlin(data : T, type : KClass<*>) : String
    fun <T: Any> deserializeKotlin(json: String, type : KClass<*>) : T
}