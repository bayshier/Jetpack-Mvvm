package com.jetpack

import android.content.Context
import androidx.multidex.MultiDex
import com.base_library.BaseApp


/**
 *  
 * @data 2020/6/26
 */
class App: BaseApp() {

    override fun onCreate() {
        super.onCreate()
        initSmartHead()
        MultiDex.install(this);
    }

    /**
     * 初始化加载刷新ui
     */
    private fun initSmartHead() {

    }
}