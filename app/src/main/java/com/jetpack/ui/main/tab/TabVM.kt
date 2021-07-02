package com.jetpack.ui.main.tab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.base_library.base.BaseViewModel

/**
 * des tab
 *   /7/7
 *  
 */
class TabVM : BaseViewModel() {

    private val repo by lazy { TabRepo() }

    /**
     * tab
     */
    private val _tabLiveData = MutableLiveData<MutableList<TabBean>>()
    val tabLiveData: LiveData<MutableList<TabBean>> = _tabLiveData

    fun getTab(type: Int) {
        launch {
            _tabLiveData.value = repo.getTab(type)
        }
    }
}