package com.jetpack.ui.my

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.base_library.base.BaseViewModel
import com.jetpack.bean.ArticleBean

/**
 *   /7/13
 *  
 */
class MyArticleVM :BaseViewModel(){

    private val repo by lazy { MyArticleRepo(viewModelScope,errorLiveData) }

    val myLiveDate = MutableLiveData<MutableList<ArticleBean.DatasBean>>()

    val deleteLiveData = MutableLiveData<Int>()

    fun getMyArticle(isRefresh:Boolean){
        repo.getMyArticle(isRefresh,myLiveDate,emptyLiveDate)
    }

    fun delete(id:Int){
        repo.delete(id,deleteLiveData)
    }
}