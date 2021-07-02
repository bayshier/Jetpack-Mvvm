package com.jetpack.ui.set

import androidx.lifecycle.MutableLiveData
import com.base_library.base.BaseRepository
import com.base_library.http.ApiException
import com.jetpack.http.ApiService
import com.jetpack.http.RetrofitManager
import com.jetpack.utils.CacheUtil
import kotlinx.coroutines.CoroutineScope

/**
 * des 设置
 *   /7/10
 *  
 */
class SetRepo(coroutineScope: CoroutineScope, errorLiveData: MutableLiveData<ApiException>) :
    BaseRepository(coroutineScope, errorLiveData) {

    /**
     * 退出登陆
     */
    fun logout(logoutLiveData : MutableLiveData<Any>){
        launch(
            block = {
                RetrofitManager.getApiService(ApiService::class.java)
                    .logout()
                    .data(Any::class.java)
            },
            success = {
                CacheUtil.resetUser()
                logoutLiveData.postValue(it)
            }
        )
    }
}