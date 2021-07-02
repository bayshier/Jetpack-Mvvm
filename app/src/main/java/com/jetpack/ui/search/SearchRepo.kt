package com.jetpack.ui.search

import com.base_library.base.BaseRepository
import com.jetpack.bean.ArticleListBean
import com.jetpack.http.ApiService
import com.jetpack.http.RetrofitManager

/**
 *
 * @data 2020/7/11
 */
class SearchRepo: BaseRepository() {

    /**
     * 页码
     */
    private var page = 0

    /**
     * 搜索
     */
    suspend fun search(keyWord: String) = withIO {
        page = 0
        RetrofitManager.getApiService(ApiService::class.java)
            .search(page,keyWord)
            .data()
            .let {
                ArticleListBean.trans(it.datas?: mutableListOf())
            }
    }

    suspend fun loadMore(keyWord: String) = withIO {
        page++
        RetrofitManager.getApiService(ApiService::class.java)
            .search(page,keyWord)
            .data()
            .let {
                ArticleListBean.trans(it.datas?: mutableListOf())
            }
    }

}