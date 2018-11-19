@file:JvmName("HttpStatus")
package com.github.fcopardo.easyrest.common

import kotlin.jvm.JvmName

class HttpStatus{

    companion object {
        const val  I_AM_A_TEAPOT = 0

        const val  CONTINUE = 100
        const val  SWITCHING_PROTOCOLS = 101
        const val  PROCESSING = 102
        const val  EARLY_HINTS = 103

        const val  OK = 200
        const val  CREATED = 201
        const val  ACCEPTED = 202
        const val  NON_AUTHORITATIVE_INFORMATION = 203
        const val  NO_CONTENT = 204
        const val  RESET_CONTENT = 205
        const val  PARTIAL_CONTENT = 206
        const val  MULTI_STATUS = 207
        const val  ALREADY_REPORTED = 208
        const val  IM_USED = 226

        const val  MULTIPLE_CHOICES = 300
        const val  MOVED_PERMANENTLY = 301
        const val  FOUND = 302
        const val  SEE_OTHER = 303
        const val  NOT_MODIFIED = 304
        const val  USE_PROXY = 305
        const val  SWITCH_PROXY = 306
        const val  TEMPORARY_REDIRECT = 307
        const val  PERMANENT_REDIRECT = 308

        const val  BAD_REQUEST = 400
        const val  UNAUTHORIZED = 401
        const val  PAYMENT_REQUIRED = 402
        const val  FORBIDDEN = 403
        const val  NOT_FOUND = 404
        const val  METHOD_NOT_ALLOWED = 405
        const val  NOT_ACCEPTABLE = 406
        const val  PROXY_AUTHORIZATION_REQUIRED = 407
        const val  REQUEST_TIMEOUT = 408
        const val  CONFLICT = 409
    }

    fun isOk(requestCode : Int) : Boolean {
        return requestCode in 200..299
    }

    fun isRedirect(requestCode : Int) : Boolean {
        return requestCode in 300..399
    }

    fun isRequestError(requestCode : Int) : Boolean {
        return requestCode in 400..499
    }

    fun isServerError(requestCode : Int) : Boolean {
        return requestCode in 500..599
    }

}