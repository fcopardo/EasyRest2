package com.github.fcopardo.easyrest.common

import com.github.fcopardo.easyrest.api.PlatformContract

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

interface Platform : PlatformContract {

    override fun deleteCache()
    fun deleteCache(classes: List<Class<*>>, maximumTime: Long)
    override fun checkConnectivity(): Boolean
    override fun getBasePath(): String
    override fun getFullPath(): String

    /**
     * Creates a SHA-1 hash from a given string.
     * @param password the string to be hashed.
     * @return a String representing the SHA-1 form of the argument.
     * @throws java.security.NoSuchAlgorithmException if the SHA-1 algorithm is absent from the JVM.
     */
    @Throws(NoSuchAlgorithmException::class)
    override fun getHashOne(password: String): String {
        val md = MessageDigest.getInstance("SHA-1")
        md.update(password.toByteArray())

        val byteData = md.digest()

        val sb = StringBuilder()
        for (i in byteData.indices) {
            sb.append(Integer.toString((byteData[i] + 0xff) + 0x100, 16)
                    .substring(1))
        }
        return sb.toString()
    }

}
