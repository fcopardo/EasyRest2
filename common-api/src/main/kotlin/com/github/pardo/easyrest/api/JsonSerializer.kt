package com.github.pardo.easyrest.api

interface JsonSerializer<T, X> {

    /**
     * writes an object into a file using JSON
     */
    fun writeValue(file: T, someObject : X)

    /**
     * Reads a file and returns the content as a T object
     */
    fun getValue(file : T) : X

}