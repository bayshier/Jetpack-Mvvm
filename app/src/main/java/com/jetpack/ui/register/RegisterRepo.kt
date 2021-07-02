package com.jetpack.ui.register

import androidx.lifecycle.MutableLiveData
import com.base_library.base.BaseRepository
import com.base_library.http.ApiException
import com.jetpack.http.ApiService
import com.jetpack.http.RetrofitManager
import kotlinx.coroutines.CoroutineScope


class RegisterRepo(coroutineScope: CoroutineScope, errorLiveData: MutableLiveData<ApiException>) :
    BaseRepository(coroutineScope, errorLiveData) {

    fun register(username: String, password: String, rePassword: String, registerLiveData : MutableLiveData<Any>) {
        launch(
            block = {
                RetrofitManager.getApiService(ApiService::class.java)
                    .register(username,password,rePassword)
                    .data()
            },
            success = {
                registerLiveData.postValue(it)
            }
        )
    }

}