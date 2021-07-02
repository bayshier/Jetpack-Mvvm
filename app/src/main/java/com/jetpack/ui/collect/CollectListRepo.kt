package com.jetpack.ui.collect

import com.base_library.base.BaseRepository
import com.jetpack.bean.ArticleListBean
import com.jetpack.http.ApiService
import com.jetpack.http.RetrofitManager

/**
 * des 收藏的文章
 *   /7/8
 *  
 */
class CollectListRepo : BaseRepository() {

    private var page = 0


    /**
     * 请求第一页
     */
    suspend fun getCollectArticle() = withIO {
        page = 0
        RetrofitManager.getApiService(ApiService::class.java)
            .getCollectData(page)
            .data()
            .let {
                ArticleListBean.transByCollect(it.datas?: mutableListOf())
            }
    }

    /**
     * 请求下一页
     */
    suspend fun loadMoreCollectArticle() = withIO {
        page++
        RetrofitManager.getApiService(ApiService::class.java)
            .getCollectData(page)
            .data()
            .let {
                ArticleListBean.transByCollect(it.datas?: mutableListOf())
            }
    }

    /**
     * 取消收藏
     */
    suspend fun unCollect(id:Int) = withIO {
        RetrofitManager.getApiService(ApiService::class.java)
            .unCollect(id)
            //如果data可能为空,可通过此方式通过反射生成对象,避免空判断
            .data(Any::class.java)
    }
}