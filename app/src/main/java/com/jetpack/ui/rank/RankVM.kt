package com.jetpack.ui.rank

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.base_library.base.BaseViewModel

/**
 * des 排名
 *   /7/13
 *  
 */
class RankVM :BaseViewModel(){

    private val repo by lazy { RankRepo(viewModelScope,errorLiveData) }

    val rankLiveData = MutableLiveData<MutableList<RankBean.DatasBean>>()

    /**
     * 获取排名
     */
    fun getRank(isRefresh:Boolean){
        repo.getRank(isRefresh,rankLiveData)
    }
}