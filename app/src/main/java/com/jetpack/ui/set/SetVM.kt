package com.jetpack.ui.set

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.base_library.base.BaseViewModel

/**
 * des 设置
 *   /7/10
 *
 */
class SetVM :BaseViewModel(){

    private val repo by lazy { SetRepo(viewModelScope,errorLiveData) }

    val logoutLiveData = MutableLiveData<Any>()

    fun logout(){
        repo.logout(logoutLiveData)
    }
}