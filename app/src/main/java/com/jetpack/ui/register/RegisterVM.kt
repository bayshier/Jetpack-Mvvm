package com.jetpack.ui.register

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.base_library.base.BaseViewModel


class RegisterVM :BaseViewModel(){

    /**
     * 用户名
     */
    val username = ObservableField<String>().apply {
        set("")
    }

    /**
     * 密码
     */
    val password = ObservableField<String>().apply {
        set("")
    }

    /**
     * 二次确认密码
     */
    val rePassword = ObservableField<String>().apply {
        set("")
    }




    /**
     * 二次确认密码是否可见
     */
    val rePassIsVisibility = ObservableField<Boolean>().apply {
        set(false)
    }

    /**
     * 密码是否可见
     */
    val passIsVisibility = ObservableField<Boolean>().apply {
        set(false)
    }

    /**
     * 注册
     */
    val registerLiveData = MutableLiveData<Any>()
    private val repo by lazy { RegisterRepo(viewModelScope,errorLiveData) }
    fun register(){
        repo.register(username.get()!!,password.get()!!,rePassword.get()!!,registerLiveData)
    }

}