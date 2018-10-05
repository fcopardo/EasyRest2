package com.github.fcopardo.easyrest.common

import java.util.*

data class Categories(var members : Array<Category>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Categories

        if (!Arrays.equals(members, other.members)) return false

        return true
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(members)
    }
}