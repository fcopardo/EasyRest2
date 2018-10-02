package com.github.fcopardo.easyrest.api

import com.github.fcopardo.easyrest.api.callbacks.*
import com.github.fcopardo.easyrest.common.HttpMethod

/**
 * Public API
 */
interface BaseRestWorker<T, X, out PlatformContract> {

    /**
     * Setter for the request's timeout
     */
    fun setTimeOut(milliseconds : Int) : BaseRestWorker<T, X, out PlatformContract>

    /**
     * Returns the argument entity.
     *
     * @return a subclass of BaseModel
     */
    fun getEntity() : T?

    /**
     * Sets the argument entity.
     *
     * @param entity a class implementing sendRestData, to be sent in the request.
     */
    fun setEntity(entity : T) : BaseRestWorker<T, X, out PlatformContract>

    /**
     * Returns the response of the rest call, in X form.
     *
     * @return an X instance of the rest call response.
     */
    fun getJsonResponseEntity() : X?

    /**
     * Sets the response entity.
     *
     * @param jsonResponseEntity an instance of X.
     */
    fun setJsonResponseEntity(jsonResponseEntity : X ) : BaseRestWorker<T, X, out PlatformContract>

    fun getErrorResponse() : String

    /**
     * Returns the Http headers to be used.
     *
     * @return an instance of HttpHeaders.
     */
    fun getRequestHeaders() : Map<String, String>

    /**
     * Sets the Http headers to be used.
     *
     * @param requestHeaders an instance of HttpHeaders.
     */
    fun setRequestHeaders(requestHeaders : Map<String, String>) : BaseRestWorker<T, X, out PlatformContract>

    /**
     * Returns the headers from the response.
     *
     * @return an HttpHeaders instance from the request response.
     */
    fun getResponseHeaders() : Map<String, String>

    fun setResponseHeaders(responseHeaders : Map<String, String>) : BaseRestWorker<T, X, out PlatformContract>

    /**
     * Returns the REST method to be called when the service call is executed.
     *
     * @return a String with the method.
     */
    fun getMethodToCall() : HttpMethod

    fun getResponseStatus() : Int

    /**
     * Allows to set the Http method to call. If a invalid or unsupported method is passed, it will be ignored.
     *
     * @param MethodToCall a valid http method.
     */
    fun setMethodToCall(MethodToCall : HttpMethod) : BaseRestWorker<T, X, out PlatformContract>

    fun addUrlParams(urlParameters : Map<String, Any> ) : BaseRestWorker<T, X, out PlatformContract>
    /**
     * Sets the URL to be used.
     *
     * @param Url the URL of the rest call.
     */
    fun setUrl(Url : String) : BaseRestWorker<T, X, out PlatformContract>

    fun getUrl() : String

    fun createUrl() : BaseRestWorker<T, X, out PlatformContract>


    /**
     * Interface. Allows to attach a body of code to be executed after a successful rest call.
     *
     * @param task a class implementing the afterTaskCompletion interface.
     */
    public fun setTaskCompletion(task : AfterTaskCompletion<X>?) : BaseRestWorker<T, X, out PlatformContract>

    /**
     * Interface. Allows to attach a body of code to be executed after a failed rest call.
     *
     * @param taskFailure a class implementing the afterTaskFailure interface.
     */
    public fun setTaskFailure(taskFailure : AfterTaskFailure?) : BaseRestWorker<T, X, out PlatformContract>

    /**
     * Interface to be executed when a server error occurs.
     *
     * @param serverTaskFailure an instance of the afterServerTaskFailure interface
     */
    public fun setServerTaskFailure(serverTaskFailure : AfterServerTaskFailure?) : BaseRestWorker<T, X, out PlatformContract>

    /**
     * Interface to be executed when a client error arises.
     *
     * @param clientTaskFailure an instance of the afterClientTaskFailure interface
     */
    public fun setClientTaskFailure(clientTaskFailure : AfterClientTaskFailure?) : BaseRestWorker<T, X, out PlatformContract>

    /**
     * Interface to be executed after all processes are finalized, no matter the result.
     *
     * @param commonTasks an instance of the commonTasks interface.
     */
    fun setCommonTasks(commonTasks : CommonTasks?) : BaseRestWorker<T, X, out PlatformContract>

    fun addHeader(header : String, value : String) : BaseRestWorker<T, X, out PlatformContract>

    fun isCacheEnabled(bol : Boolean) : BaseRestWorker<T, X, out PlatformContract>

    fun setCacheTime(time : Long) : BaseRestWorker<T, X, out PlatformContract>

    fun setReprocessWhenRefreshing(reprocessWhenRefreshing : Boolean) : BaseRestWorker<T, X, out PlatformContract>

    fun setAutomaticCacheRefresh(automaticCacheRefresh : Boolean) : BaseRestWorker<T, X, out PlatformContract>

    fun isFullAsync() : Boolean

    fun setFullAsync(fullAsync : Boolean) : BaseRestWorker<T, X, out PlatformContract>

    fun execute(isAsync : Boolean)

}