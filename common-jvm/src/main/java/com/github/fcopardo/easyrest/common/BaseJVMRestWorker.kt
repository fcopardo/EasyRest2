package com.github.fcopardo.easyrest.common

import com.github.fcopardo.easyrest.api.RestWorker
import com.github.fcopardo.easyrest.api.callbacks.*
import com.github.pardo.easyrest.api.DoubleSerializer
import com.github.pardo.easyrest.api.KotlinSerializer
import com.github.pardo.easyrest.api.Serializer
import com.github.pardo.easyrest.common.MediaTypes
import java.io.File
import java.net.URI
import java.util.*
import okhttp3.*
import java.io.IOException
import kotlin.reflect.KClass

abstract class BaseJVMRestWorker<T, X, Z> : RestWorker<T, X, Z> {

    protected val entityClass : Class<T>
    protected val jsonResponseEntityClass : Class<X>
    private var platform : Z? = null
    protected var mapper : DoubleSerializer<File, X>?= null
    protected var mySerializer : KotlinJVMSerializer? = null
    private var milliseconds : Int = 5000
    private var entity : T? = null
    private var jsonResponseEntity : X? = null
    private var afterTaskCompletion : AfterTaskCompletion<X>? = null
    private var afterTaskFailure : AfterTaskFailure? = null
    private var afterServerTaskFailure : AfterServerTaskFailure? = null
    private var afterClientTaskFailure : AfterClientTaskFailure? = null
    private var commonTasks : CommonTasks? = null
    private var errorResponse : String = ""
    private var requestHeaders : HashMap<String, String> = HashMap()
    private var responseHeaders : HashMap<String, String> = HashMap()
    private var httpMethod : HttpMethod = HttpMethod.POST
    private var responseStatus : Int = HttpStatus.I_AM_A_TEAPOT
    private var urlParameters : HashMap<String, Any> = HashMap()
    private var url : String = ""
    private var cacheEnabled: Boolean = false
    private var automaticCacheRefresh: Boolean = false
    private var fullAsync: Boolean = false
    private var reprocessWhenRefreshing: Boolean = false
    private var cacheTime: Long = 0
    private var result : Boolean = false
    protected var cachedFile : String = ""

    constructor(platform : Z, entityClass : Class<T>, responseClass : Class<X>){
        this.platform = platform
        this.entityClass = entityClass
        this.jsonResponseEntityClass = responseClass
    }

    fun getPlatform() : Z? {
        return platform
    }

    fun setSerializer(serializer: KotlinJVMSerializer) : BaseJVMRestWorker<T, X, Z> {
        this.mySerializer = serializer
        return this
    }

    fun setJsonSerializer(serializer: DoubleSerializer<File, X>): BaseJVMRestWorker<T, X, Z> {
        this.mapper = serializer
        return this
    }

    override fun setTimeOut(milliseconds: Int): RestWorker<T, X, Z> {
        this.milliseconds = milliseconds
        return this
    }

    override fun getEntity(): T? {
        return entity
    }

    override fun setEntity(entity: T): RestWorker<T, X, Z> {
        this.entity = entity
        return this
    }

    override fun getJsonResponseEntity(): X? {
        return jsonResponseEntity
    }

    override fun setJsonResponseEntity(jsonResponseEntity: X): RestWorker<T, X, Z> {
        this.jsonResponseEntity = jsonResponseEntity
        return this
    }

    override fun getErrorResponse(): String {
        return errorResponse
    }

    override fun getRequestHeaders(): Map<String, String> {
        return requestHeaders
    }

    override fun setRequestHeaders(requestHeaders: Map<String, String>): RestWorker<T, X, Z> {
        this.requestHeaders.clear()
        this.requestHeaders.putAll(requestHeaders)
        return this
    }

    override fun getResponseHeaders(): Map<String, String> {
        return responseHeaders
    }

    override fun setResponseHeaders(responseHeaders: Map<String, String>): RestWorker<T, X, Z> {
        this.responseHeaders.clear()
        this.responseHeaders.putAll(responseHeaders)
        return this
    }

    override fun getMethodToCall(): HttpMethod {
        return httpMethod
    }

    override fun setMethodToCall(MethodToCall: HttpMethod): RestWorker<T, X, Z> {
        this.httpMethod = MethodToCall
        return this
    }

    override fun getResponseStatus(): Int {
        return responseStatus
    }

    override fun addUrlParams(urlParameters: Map<String, Any>): RestWorker<T, X, Z> {
        this.urlParameters.putAll(urlParameters)
        return this
    }

    override fun setUrl(Url: String): RestWorker<T, X, Z> {
        this.url = Url
        return this
    }

    override fun getUrl(): String {
        return this.url
    }

    override fun createUrl(): RestWorker<T, X, Z>{
        if (urlParameters != null && !urlParameters.isEmpty()) {
            val customUrl = getUrl()
            val builder = StringBuilder()

            var separator = "?"

            builder.append(customUrl)
            for (key in urlParameters.keys) {
                var value = urlParameters[key]

                if (value.toString().contains(" ")) {
                    value = value.toString().replace(" ", "+")
                }

                builder.append(separator)
                builder.append(key)
                builder.append("=")
                builder.append(value)

                separator = "&"
            }
            setUrl(builder.toString())
        }
        return this
    }

    fun getURI(): URI {
        return URI(url)
    }

    override fun setTaskCompletion(task: AfterTaskCompletion<X>?): RestWorker<T, X, Z> {
        this.afterTaskCompletion = task
        return this
    }

    override fun setTaskFailure(taskFailure: AfterTaskFailure?): RestWorker<T, X, Z> {
        this.afterTaskFailure = taskFailure
        return this
    }

    override fun setServerTaskFailure(serverTaskFailure: AfterServerTaskFailure?): RestWorker<T, X, Z> {
        this.afterServerTaskFailure = serverTaskFailure
        return this
    }

    override fun setClientTaskFailure(clientTaskFailure: AfterClientTaskFailure?): RestWorker<T, X, Z> {
        this.afterClientTaskFailure = clientTaskFailure
        return this
    }

    override fun setCommonTasks(commonTasks: CommonTasks?): RestWorker<T, X, Z> {
        this.commonTasks = commonTasks
        return this
    }

    override fun addHeader(header: String, value: String): RestWorker<T, X, Z> {
        this.requestHeaders.put(header, value)
        return this
    }

    override fun isCacheEnabled(bol: Boolean): RestWorker<T, X, Z> {
        this.cacheEnabled = bol
        return this
    }

    override fun setCacheTime(time: Long): RestWorker<T, X, Z> {
        this.cacheTime = time
        return this
    }

    override fun setReprocessWhenRefreshing(reprocessWhenRefreshing: Boolean): RestWorker<T, X, Z> {
        this.reprocessWhenRefreshing = reprocessWhenRefreshing
        return this
    }

    override fun setAutomaticCacheRefresh(automaticCacheRefresh: Boolean): RestWorker<T, X, Z> {
        this.automaticCacheRefresh = automaticCacheRefresh
        return this
    }

    override fun isFullAsync(): Boolean {
        return this.fullAsync
    }

    override fun setFullAsync(fullAsync: Boolean): RestWorker<T, X, Z> {
        this.fullAsync = fullAsync
        return this
    }

    protected fun setResult(value : Boolean){
        this.result = value
    }

    protected fun getResult() : Boolean {
        return result
    }

    abstract fun getCachedFileName(): String
    abstract fun showMessage(title : String, message : String)

    protected fun doCall(){

        createUrl()
        val client = OkHttpClient()
        var requestBuilder = Request.Builder().url(getUrl())
        when(httpMethod){

            HttpMethod.POST->{
                val kClass : KClass<*> = when {
                    entityClass.canonicalName == Void.TYPE.canonicalName -> Void.TYPE.kotlin
                    else -> Class.forName(entityClass.canonicalName).kotlin
                }
                requestBuilder.put(RequestBody.create(MediaTypes.JSON, mySerializer?.serialize(entity, kClass)))
            }
        }
        var call = client.newCall(Request.Builder()
                .url(getUrl())
                .build())

        makeCall(call)
    }

    protected fun makeCall(call : Call){
        call.enqueue(object : Callback{
            override fun onResponse(call: Call, response: Response) {
                /*val kClass : KClass<*> = when {
                    jsonResponseEntityClass.canonicalName == Void.TYPE.canonicalName -> Void.TYPE.kotlin
                    else -> Class.forName(jsonResponseEntityClass.canonicalName).kotlin
                }*/
                var result = false
                when{
                    response.code() in 200..299 ->{
                        afterTaskCompletion?.onTaskCompleted(
                                //mySerializer?.deserialize(response.body()!!.string(), kClass)!!
                                mySerializer?.deserialize(response.body()!!.string(), jsonResponseEntityClass)!!
                        )
                        result = true
                    }
                    response.code() in 400..499  -> afterClientTaskFailure?.onClientTaskFailed(response.code())
                    response.code() in 500..599 -> afterServerTaskFailure?.onServerTaskFailed(response.code())
                }
                commonTasks?.performCommonTask(result, response.code())
            }

            override fun onFailure(call: Call, e: IOException) {
                afterTaskFailure?.onTaskFailed(e)
            }
        })
    }

    override fun execute(isAsync: Boolean) {
        doCall()
    }
}