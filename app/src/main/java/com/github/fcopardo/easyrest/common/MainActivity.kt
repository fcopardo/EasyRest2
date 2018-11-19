package com.github.fcopardo.easyrest.common

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.github.fcopardo.easyrest.R
import com.github.fcopardo.easyrest.android.AndroidPlatform
import com.github.fcopardo.easyrest.android.AndroidRestWorker
import com.github.fcopardo.easyrest.api.callbacks.AfterTaskCompletion
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Type
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action") {
                        makeCall()
                    }.show()
        }
        makeCall()
    }

    fun makeCall(){
        var platform : AndroidPlatform = AndroidPlatform().setApplication(this.application)

        var serializer = object : KotlinJVMSerializer{

            var customSerializer = object : JsonDeserializer<Categories>{
                override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Categories {

                    var jArray = json?.asJsonArray
                    var resultArray = ArrayList<Category>()

                    var result = Categories()

                    for(node : JsonElement in jArray!!){
                        var aNode = node.asJsonObject
                        lateinit var category : Category
                        val myType = object : TypeToken<Category>() {}.type
                        category = Gson().fromJson(aNode, myType)
                        resultArray.add(category)
                    }

                    result.members = resultArray
                    return result
                }

            }

            val gson = GsonBuilder().registerTypeAdapter(Categories::class.java, customSerializer).create()

            override fun <T> deserialize(json: String, type: Class<T>): T {

                var reflectionSerializer = ReflectionSerializer()

                if(json.startsWith("[")){
                    /*var jsonArray = JSONArray(json)

                    for(i in jsonArray.length()-1 downTo 0 step 1){
                        Log.e("json org", jsonArray.get(i).toString())
                    }*/
                    //var o = reflectionSerializer.deserialize(json, Array<Category>::class.java)
                    var o = reflectionSerializer.deserialize(json, type)

                }else{
                    var jsonObject = JSONObject(json)
                    var o = reflectionSerializer.deserialize(json, type)
                }


                return gson.fromJson(json, type)
            }

            override fun <T> serialize(data: T, type: Class<T>): String {
                return gson.toJson(data)
            }

            override fun <T> deserialize(json: String, type: KClass<*>): T {
                return gson.fromJson(json, type::class.java) as T
            }

            override fun <T> serialize(data: T, type: KClass<*>): String {
                return gson.toJson(data)
            }
        }
        var restWorker : AndroidRestWorker<Void, Array<Category>, AndroidPlatform>
                = AndroidRestWorker(
                platform,
                Void::class.java,
                Array<Category>::class.java
        )

        restWorker
                .setSerializer(ReflectionSerializer())
                .setUrl("https://api.mercadolibre.com/sites/MLA/categories")
                .setMethodToCall(HttpMethod.GET)
                .setTaskCompletion(object : AfterTaskCompletion<Array<Category>>{
                    override fun onTaskCompleted(result: Array<Category>) {
                        for (item : Category in result){
                            Log.e("category", item.id+" - "+item.name)
                        }
                    }
                })
                .execute(true)
    }

}
