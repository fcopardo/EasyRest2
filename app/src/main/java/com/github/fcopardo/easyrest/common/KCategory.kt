package com.github.fcopardo.easyrest.common

class KCategory{

    private var id : String = ""
    private var name : String = ""

    fun setId(id: String){
        this.id = id
    }

    fun getId() : String {
        return id
    }

    fun setName(name : String){
        this.name = name
    }

    fun getName() : String {
        return name
    }

}