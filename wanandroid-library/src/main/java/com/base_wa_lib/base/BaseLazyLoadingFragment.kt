package com.base_wa_lib.base

import android.content.Context
import android.view.ViewGroup
import com.base_library.base.LazyVmFragment
import com.base_library.utils.StatusUtils
import com.base_wa_lib.view.LoadingTip

/**
 *
 * @date 2021/4/21
 */
abstract class BaseLazyLoadingFragment : LazyVmFragment() {

    protected var gloding: LoadingTip? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseLoadingActivity) {
            gloding = context.loadingTip
        }
    }

    /**
     * 设置loadingView上下变局
     */
    protected fun setLoadingMargin(topMargin: Int, bottomMargin: Int) {
        val loadMarginTop = StatusUtils.getStatusBarHeight(mActivity) +topMargin
        val loadMarginBottom =  StatusUtils.getNavigationBarHeight(mActivity) + bottomMargin
        val params = gloding?.layoutParams as ViewGroup.MarginLayoutParams
        params.topMargin = loadMarginTop
        params.bottomMargin = loadMarginBottom
        gloding?.layoutParams = params
    }
}