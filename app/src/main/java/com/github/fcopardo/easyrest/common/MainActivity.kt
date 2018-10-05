package com.github.fcopardo.easyrest.common

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.github.fcopardo.easyrest.R
import com.github.fcopardo.easyrest.android.AndroidPlatform
import com.github.fcopardo.easyrest.android.AndroidRestWorker

import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action") {

                        var platform : AndroidPlatform = AndroidPlatform().setApplication(this.application)
                        var restWorker : AndroidRestWorker<Void, Categories, AndroidPlatform>
                                = AndroidRestWorker(
                                platform,
                                Void::class.java,
                                Categories::class.java
                        )

                        restWorker
                                .setUrl("https://api.mercadolibre.com/sites/MLA/categories")
                                .setMethodToCall(HttpMethod.GET)
                                .execute(true)

                    }.show()
        }
    }

}
