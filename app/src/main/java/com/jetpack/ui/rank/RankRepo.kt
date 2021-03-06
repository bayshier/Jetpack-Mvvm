package com.jetpack.ui.rank

import androidx.lifecycle.MutableLiveData
import com.base_library.base.BaseRepository
import com.base_library.http.ApiException
import com.jetpack.http.ApiService
import com.jetpack.http.RetrofitManager
import kotlinx.coroutines.CoroutineScope

/**
 * des 排名
 *   /7/13
 *
 */
class RankRepo(coroutineScope: CoroutineScope, errorLiveData: MutableLiveData<ApiException>) :
    BaseRepository(coroutineScope, errorLiveData) {

    private var page = 1
    fun getRank(
        isRefresh: Boolean,
        rankLiveData: MutableLiveData<MutableList<RankBean.DatasBean>>
    ) {
        launch(
            block = {
                if (isRefresh) {
                    page = 1
                } else {
                    page++
                }
                RetrofitManager.getApiService(ApiService::class.java)
                    .getRank(page)
                    .data()
            },
            success = {
                //做数据累加
                rankLiveData.value.apply {
                    //第一次加载 或 刷新 给 articleLiveData 赋予一个空集合
                    val currentList = if (isRefresh || this == null){
                        mutableListOf()
                    }else{
                        this
                    }
                    it.datas?.let { it1 -> currentList.addAll(it1) }
                    rankLiveData.postValue(currentList)
                }
            }
        )
    }
}