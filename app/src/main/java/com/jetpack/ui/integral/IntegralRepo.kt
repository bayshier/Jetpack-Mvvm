package com.jetpack.ui.integral

import com.base_library.base.BaseRepository
import com.jetpack.http.ApiService
import com.jetpack.http.RetrofitManager

/**
 * des 积分
 *   /7/8
 *  
 */
class IntegralRepo : BaseRepository() {

    private var page = 1

    /**
     * 获取积分
     */
    suspend fun getIntegral() = withIO {
        page = 1
        RetrofitManager.getApiService(ApiService::class.java)
            .getIntegralRecord(page)
            .data()
            .let {
                IntegralListBean.trans(it.datas?: mutableListOf())
            }
    }

    /**
     * 获取下一页积分
     */
    suspend fun loadMore() = withIO {
        page++
        RetrofitManager.getApiService(ApiService::class.java)
            .getIntegralRecord(page)
            .data()
            .let {
                IntegralListBean.trans(it.datas?: mutableListOf())
            }
    }
}