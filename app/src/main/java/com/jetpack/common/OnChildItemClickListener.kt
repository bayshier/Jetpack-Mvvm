package com.jetpack.common

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter

/**
 * des 适配器中item子view点击⌚️
 *   /6/1
 *
 */
interface OnChildItemClickListener {
    fun onItemChildClick(adapter: BaseQuickAdapter<*,*>, view: View, position:Int )
}