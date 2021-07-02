package com.jetpack.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.jetpack.R

/**
 * des 文章列表dataBinding适配器
 *   /6/28
 *
 */
object ArticleBindAdapter {

    /**
     * 加载图片,做高斯模糊处理
     */
    @BindingAdapter(value = ["articleCollect"])
    @JvmStatic
    fun imgPlayBlur(view: ImageView, collect: Boolean) {
        if (collect) {
            view.setImageResource(R.mipmap._collect)
        } else {
            view.setImageResource(R.mipmap.un_collect)
        }
    }

}