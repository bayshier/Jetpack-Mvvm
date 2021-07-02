package com.jetpack.ui.collect

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.base_library.base.BaseViewModel
import com.jetpack.bean.ArticleListBean

/**
 * des 文章vm
 *   /7/8
 *
 */
class CollectVM : BaseViewModel() {
    private val repo by lazy { CollectListRepo() }


    /**
     * 收藏的的文章
     */
    private val _articleLiveData = MutableLiveData<MutableList<ArticleListBean>>()
    val articleLiveData: LiveData<MutableList<ArticleListBean>> = _articleLiveData

    /**
     * 获取收藏列表
     */
    fun getCollect() {
        launch {
            _articleLiveData.value = repo.getCollectArticle()
        }
    }

    fun loadMoreCollect() {
        launch {
            val list = _articleLiveData.value
            list?.addAll(repo.loadMoreCollectArticle())
            _articleLiveData.value = list
            handleList(_articleLiveData)
        }
    }

    /**
     * 取消收藏
     */
    fun unCollect(id: Int) {
        launch {
            repo.unCollect(id).let {
                val list = _articleLiveData.value
                list?.find {
                    it.id == id
                }?.let {
                    list.remove(it)
                }
                _articleLiveData.value = list
            }
        }
    }
}