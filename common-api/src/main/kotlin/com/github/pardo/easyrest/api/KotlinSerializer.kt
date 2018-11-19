package com.github.pardo.easyrest.api

import com.github.pardo.easyrest.api.Serializer
//import kotlinx.serialization.json.JSON
import kotlin.reflect.KClass

/**
 * KotlinX serializator
 */
interface KotlinSerializer : Serializer {
    /*override fun <T : Any> serializeKotlin(data : T, type : KClass<*>) : String{
        return JSON.stringify(data)
    }
    override fun <T: Any> deserializeKotlin(json: String, type : KClass<*>) : T {
        return JSON.parse(json)
    }*/
}