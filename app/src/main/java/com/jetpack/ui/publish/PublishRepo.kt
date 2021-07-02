package com.jetpack.ui.publish

import androidx.lifecycle.MutableLiveData
import com.base_library.base.BaseRepository
import com.base_library.http.ApiException
import com.jetpack.http.ApiService
import com.jetpack.http.RetrofitManager
import kotlinx.coroutines.CoroutineScope

/**
 * des 发布
 *  
 * @data 2020/7/12
 */
class PublishRepo(coroutineScope: CoroutineScope, errorLiveData: MutableLiveData<ApiException>) :
    BaseRepository(coroutineScope, errorLiveData) {


    /**
     * 发布
     * @param title 文章标题
     * @param link  文章链接
     */
    fun publish(title:String,link:String,publishLiveData : MutableLiveData<Any>){
        launch(
            block = {
                RetrofitManager.getApiService(ApiService::class.java)
                    .publishArticle(title,link)
                    .data(Any::class.java)
            },
            success = {
                publishLiveData.postValue(it)
            }
        )
    }
}