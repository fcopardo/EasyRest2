package com.github.fcopardo.easyrest.common

import com.github.pardo.easyrest.api.JsonSerializer
import java.io.File

interface JsonFileSerializer<T, X> : JsonSerializer<File, X> {
    /**
     * writes an object into a file using JSON
     */
    override fun writeValue(file: File, someObject : X)

    /**
     * Reads a file and returns the content as a T object
     */
    override fun getValue(file : File) : X
    fun getValue(file : File, aClass: Class<X>) : X
}