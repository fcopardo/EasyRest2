package com.github.fcopardo.easyrest.common

import com.github.fcopardo.easyrest.common.callbacks.*
import java.net.URI
import java.time.Year

open class BaseJVMRestWorker<T, X> : RestWorker<T, X> {

    @JvmField var milliseconds : Int = 5000
    @JvmField var entity : T? = null
    @JvmField var jsonResponseEntity : X? = null

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRequestHeaders(): Map<String, String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setRequestHeaders(requestHeaders: Map<String, String>): RestWorker<T, X> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getResponseHeaders(): Map<String, String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setResponseHeaders(responseHeaders: Map<String, String>): RestWorker<T, X> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMethodToCall(): HttpMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getResponseStatus(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setMethodToCall(MethodToCall: HttpMethod): RestWorker<T, X> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addUrlParams(urlParameters: Map<String, Object>): RestWorker<T, X> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setUrl(Url: String): RestWorker<T, X> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUrl(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getURI(): URI {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTaskCompletion(task: AfterTaskCompletion<X>): RestWorker<T, X> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTaskFailure(taskFailure: AfterTaskFailure): RestWorker<T, X> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setServerTaskFailure(serverTaskFailure: AfterServerTaskFailure): RestWorker<T, X> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setClientTaskFailure(clientTaskFailure: AfterClientTaskFailure): RestWorker<T, X> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setCommonTasks(commonTasks: CommonTasks): RestWorker<T, X> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addHeader(header: String, value: String): RestWorker<T, X> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isCacheEnabled(bol: Boolean): RestWorker<T, X> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setCacheTime(time: Long): RestWorker<T, X> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setReprocessWhenRefreshing(reprocessWhenRefreshing: Boolean): RestWorker<T, X> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAutomaticCacheRefresh(automaticCacheRefresh: Boolean): RestWorker<T, X> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isFullAsync(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setFullAsync(fullAsync: Boolean): RestWorker<T, X> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}