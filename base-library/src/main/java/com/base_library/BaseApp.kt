package com.base_library

import android.app.Application
import android.content.Context

/**
 *   /5/9
 *  
 */
open class BaseApp :Application() {


    override fun onCreate() {
        super.onCreate()
        baseApplication = this
    }

    companion object{
        private lateinit var baseApplication:BaseApp

        fun getContext(): Context {
            return baseApplication
        }
    }
}