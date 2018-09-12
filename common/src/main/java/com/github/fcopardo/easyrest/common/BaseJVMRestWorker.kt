package com.github.fcopardo.easyrest.common

import com.github.fcopardo.easyrest.common.callbacks.*
import java.net.URI

open class BaseJVMRestWorker<T, X> : RestWorker<T, X> {

    @JvmField var milliseconds : Int = 5000
    @JvmField var entity : T? = null
    @JvmField var jsonResponseEntity : X? = null
    @JvmField var afterTaskCompletion : AfterTaskCompletion<X>? = null
    @JvmField var afterTaskFailure : AfterTaskFailure? = null
    @JvmField var afterServerTaskFailure : AfterServerTaskFailure? = null
    @JvmField var afterClientTaskFailure : AfterClientTaskFailure? = null
    @JvmField var commonTasks : CommonTasks? = null
    @JvmField var errorResponse : String = ""
    @JvmField var requestHeaders : HashMap<String, String> = HashMap()
    @JvmField var responseHeaders : HashMap<String, String> = HashMap()
    @JvmField var methodToCall : HttpMethod = HttpMethod.POST
    @JvmField var responseStatus : Int = HttpStatus.I_AM_A_TEAPOT
    @JvmField var urlParameters : HashMap<String, Any> = HashMap()
    @JvmField var url : String = ""
    @JvmField var cacheEnabled: Boolean = false
    @JvmField var automaticCacheRefresh: Boolean = false
    @JvmField var fullAsync: Boolean = false
    @JvmField var reprocessWhenRefreshing: Boolean = false
    @JvmField var cacheTime: Long = 0


    override fun setTimeOut(milliseconds: Int): RestWorker<T, X> {
        this.milliseconds = milliseconds
        return this
    }

    override fun getEntity(): T? {
        return entity
    }

    override fun setEntity(entity: T): RestWorker<T, X> {
        this.entity = entity
        return this
    }

    override fun getJsonResponseEntity(): X? {
        return jsonResponseEntity
    }

    override fun setJsonResponseEntity(jsonResponseEntity: X): RestWorker<T, X> {
        this.jsonResponseEntity = jsonResponseEntity
        return this
    }

    override fun getErrorResponse(): String {
        return errorResponse
    }

    override fun getRequestHeaders(): Map<String, String> {
        return requestHeaders
    }

    override fun setRequestHeaders(requestHeaders: Map<String, String>): RestWorker<T, X> {
        this.requestHeaders.clear()
        this.requestHeaders.putAll(requestHeaders)
        return this
    }

    override fun getResponseHeaders(): Map<String, String> {
        return responseHeaders
    }

    override fun setResponseHeaders(responseHeaders: Map<String, String>): RestWorker<T, X> {
        this.responseHeaders.clear()
        this.responseHeaders.putAll(responseHeaders)
        return this
    }

    override fun getMethodToCall(): HttpMethod {
        return methodToCall
    }

    override fun setMethodToCall(MethodToCall: HttpMethod): RestWorker<T, X> {
        this.methodToCall = MethodToCall
        return this
    }

    override fun getResponseStatus(): Int {
        return responseStatus
    }

    override fun addUrlParams(urlParameters: Map<String, Any>): RestWorker<T, X> {
        this.urlParameters.putAll(urlParameters)
        return this
    }

    override fun setUrl(Url: String): RestWorker<T, X> {
        this.url = Url
        return this
    }

    override fun getUrl(): String {
        return this.url
    }

    override fun getURI(): URI {
        return URI(url)
    }

    override fun setTaskCompletion(task: AfterTaskCompletion<X>?): RestWorker<T, X> {
        this.afterTaskCompletion = task
        return this
    }

    override fun setTaskFailure(taskFailure: AfterTaskFailure?): RestWorker<T, X> {
        this.afterTaskFailure = taskFailure
        return this
    }

    override fun setServerTaskFailure(serverTaskFailure: AfterServerTaskFailure?): RestWorker<T, X> {
        this.afterServerTaskFailure = serverTaskFailure
        return this
    }

    override fun setClientTaskFailure(clientTaskFailure: AfterClientTaskFailure?): RestWorker<T, X> {
        this.afterClientTaskFailure = clientTaskFailure
        return this
    }

    override fun setCommonTasks(commonTasks: CommonTasks?): RestWorker<T, X> {
        this.commonTasks = commonTasks
        return this
    }

    override fun addHeader(header: String, value: String): RestWorker<T, X> {
        this.requestHeaders.put(header, value)
        return this
    }

    override fun isCacheEnabled(bol: Boolean): RestWorker<T, X> {
        this.cacheEnabled = bol
        return this
    }

    override fun setCacheTime(time: Long): RestWorker<T, X> {
        this.cacheTime = time
        return this
    }

    override fun setReprocessWhenRefreshing(reprocessWhenRefreshing: Boolean): RestWorker<T, X> {
        this.reprocessWhenRefreshing = reprocessWhenRefreshing
        return this
    }

    override fun setAutomaticCacheRefresh(automaticCacheRefresh: Boolean): RestWorker<T, X> {
        this.automaticCacheRefresh = automaticCacheRefresh
        return this
    }

    override fun isFullAsync(): Boolean {
        return this.fullAsync
    }

    override fun setFullAsync(fullAsync: Boolean): RestWorker<T, X> {
        this.fullAsync = fullAsync
        return this
    }
}