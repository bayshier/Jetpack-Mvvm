package com.jetpack.ui.common

import androidx.lifecycle.MutableLiveData
import com.jetpack.bean.ArticleListBean


class CollectRequest(private val listLiveData: MutableLiveData<MutableList<ArticleListBean>>) {

    private val repo by lazy { CollectRepo() }

    /**
     * 收藏
     */
    suspend fun collect(id: Int) {
        repo.collect(id)
        val list = listLiveData.value
        list?.map {
            if (id == it.id) {
                it.copy(collect = true)
            } else {
                it
            }
        }?.toMutableList().let {
            listLiveData.value = it
        }
    }

    /**
     * 取消收藏
     */
    suspend fun unCollect(id: Int) {
        repo.unCollect(id)
        val list = listLiveData.value
        list?.map {
            if (id == it.id) {
                it.copy(collect = false)
            } else {
                it
            }
        }?.toMutableList().let {
            listLiveData.value = it
        }
    }

}