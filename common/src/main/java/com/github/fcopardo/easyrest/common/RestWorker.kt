package com.github.fcopardo.easyrest.common

/**
 * Public API
 */
interface RestWorker<T, X, M> {

    /**
     * Setter for the request's timeout
     */
    fun setTimeOut(miliseconds : Int) : RestWorker<T, X, M>

    /**
     * Returns the argument entity.
     *
     * @return a subclass of BaseModel
     */
    fun getEntity() : T

    /**
     * Sets the argument entity.
     *
     * @param entity a class implementing sendRestData, to be sent in the request.
     */
    fun setEntity(entity : T) : RestWorker<T, X, M>

    /**
     * Returns the response of the rest call, in X form.
     *
     * @return an X instance of the rest call response.
     */
    fun getJsonResponseEntity() : X

    /**
     * Sets the response entity.
     *
     * @param jsonResponseEntity an instance of X.
     */
    fun setJsonResponseEntity(jsonResponseEntity : X ) : RestWorker<T, X, M>

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
    fun setRequestHeaders(requestHeaders : Map<String, String>) : RestWorker<T, X, M>

    /**
     * Returns the headers from the response.
     *
     * @return an HttpHeaders instance from the request response.
     */
    fun getResponseHeaders() : Map<String, String>

    fun setResponseHeaders(responseHeaders : Map<String, String>) : RestWorker<T, X, M>

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
    fun setMethodToCall(MethodToCall : HttpMethod) : RestWorker<T, X, M>

    fun addUrlParams(urlParameters : Map<String, Object> ) : RestWorker<T, X, M>
    /**
     * Sets the URL to be used.
     *
     * @param Url the URL of the rest call.
     */
    fun setUrl(Url : String) : RestWorker<T,X,M>

    fun getUrl() : String

    fun getURI() : URI

    /**
     * Interface. Allows to attach a body of code to be executed after a successful rest call.
     *
     * @param task a class implementing the afterTaskCompletion interface.
     */
    public fun setTaskCompletion(task : afterTaskCompletion<X>) : RestWorker<T, X, M>

    /**
     * Interface. Allows to attach a body of code to be executed after a failed rest call.
     *
     * @param taskFailure a class implementing the afterTaskFailure interface.
     */
    public fun setTaskFailure(taskFailure : afterTaskFailure) : RestWorker<T, X, M>

    /**
     * Interface to be executed when a server error occurs.
     *
     * @param serverTaskFailure an instance of the afterServerTaskFailure interface
     */
    public fun setServerTaskFailure(serverTaskFailure : afterServerTaskFailure<M>) : RestWorker<T, X, M>

    /**
     * Interface to be executed when a client error arises.
     *
     * @param clientTaskFailure an instance of the afterClientTaskFailure interface
     */
    public fun setClientTaskFailure(clientTaskFailure : afterClientTaskFailure<M>) : RestWorker<T, X, M>
    
    /**
     * Interface to be executed after all processes are finalized, no matter the result.
     *
     * @param commonTasks an instance of the commonTasks interface.
     */
    fun setCommonTasks(commonTasks : CommonTasks) : RestWorker<T, X, M>

    fun addHeader(header : String, value : String) : RestWorker<T, X, M>

    fun isCacheEnabled(bol : Boolean) : RestWorker<T, X, M>
    
    fun setCacheTime(time : Long) : RestWorker<T, X, M>

    fun setReprocessWhenRefreshing(reprocessWhenRefreshing : Boolean) : RestWorker<T, X, M>

    fun setAutomaticCacheRefresh(boolean automaticCacheRefresh : Boolean) : RestWorker<T, X, M>

    fun isFullAsync() : Boolean

    fun setFullAsync(fullAsync : Boolean) : RestWorker<T, X, M>

}