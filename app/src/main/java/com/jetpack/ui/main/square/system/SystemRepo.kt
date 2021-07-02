package com.jetpack.ui.main.square.system

import com.base_library.base.BaseRepository
import com.jetpack.bean.ArticleListBean
import com.jetpack.http.ApiService
import com.jetpack.http.RetrofitManager

/**
 *   /7/10
 *  
 */
class SystemRepo : BaseRepository() {

    /**
     * 获取体系列表
     */
    suspend fun getSystemList() = withIO {
        RetrofitManager.getApiService(ApiService::class.java)
            .getSystemList()
            .data()
    }

    /**
     * 页码
     */
    private var page = 0

    /**
     * 获取体系对应的文章
     */
    suspend fun getArticleList(id:Int) = withIO {
        page = 0
        RetrofitManager.getApiService(ApiService::class.java)
            .getSystemArticle(page,id)
            .data()
            .let {
                ArticleListBean.trans(it.datas?: mutableListOf())
            }
    }

    /**
     * 分页加载体系对应的文章
     */
    suspend fun loadMoreArticle(id:Int) = withIO {
        page++
        RetrofitManager.getApiService(ApiService::class.java)
            .getSystemArticle(page,id)
            .data()
            .let {
                ArticleListBean.trans(it.datas?: mutableListOf())
            }
    }
}