package com.jetpack.ui.main.tab

import com.base_library.base.BaseRepository
import com.jetpack.constants.Constants
import com.jetpack.http.ApiService
import com.jetpack.http.RetrofitManager

/**
 * des tab
 *   /7/7
 *  
 */
class TabRepo : BaseRepository() {


    suspend fun getTab(type: Int) = withIO {
        if (type == Constants.PROJECT_TYPE) {
            RetrofitManager.getApiService(ApiService::class.java)
                .getProjectTabList()
                .data()
        } else {
            RetrofitManager.getApiService(ApiService::class.java)
                .getAccountTabList()
                .data()
        }
    }
}
