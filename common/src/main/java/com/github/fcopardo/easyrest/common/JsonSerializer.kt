package com.github.fcopardo.easyrest.common

import java.io.File

interface JsonSerializer<X> {
    /**
     * writes an object into a file using JSON
     */
    fun writeValue(file: File, someObject : X)

    /**
     * Reads a file and returns the content as a T object
     */
    fun getValue(file : File, aClass: Class<X>)
}