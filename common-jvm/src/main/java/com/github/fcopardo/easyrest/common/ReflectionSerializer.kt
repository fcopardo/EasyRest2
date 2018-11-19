package com.github.fcopardo.easyrest.common

import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Field
import kotlin.reflect.KClass

class ReflectionSerializer :  KotlinJVMSerializer{

    override fun <T> deserialize(json: String, type: KClass<*>): T {
        var myClass : Class<T> = type.java as Class<T>
        return deserialize(json, myClass)
    }

    override fun <T> deserialize(json: String, type: Class<T>): T {

        if(type.isArray){
            var innerType = type.javaClass.componentType
            var jsonArray : JSONArray = JSONArray(json)
            var myArray = ArrayList<Any>()
            for(i in jsonArray.length()-1 downTo 0 step 1){
                var newObject = deserialize(jsonArray.get(i).toString(), type)
                myArray.add(newObject!!)
            }
            return myArray as T
        }else{
            var anObject : T = type.newInstance()
            var jsonObject = JSONObject(json)

            for(field : Field in type.fields){
                type.getDeclaredField(field.name).set(anObject, jsonObject.get(field.name))
            }

            return anObject
        }
    }

    override fun <T> serialize(data: T, type: KClass<*>): String {
        var myClass : Class<T> = type.java as Class<T>
        return serialize(data, myClass)
    }

    override fun <T> serialize(data: T, type: Class<T>): String {

        var json : String = ""
        for(field : Field in type.fields){
            json = json+"\""+field.name+"\": \""+field.get(data)+"\""+"\n"
        }
        return json
    }

}