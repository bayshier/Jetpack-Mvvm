package com.jetpack.ui.history

import androidx.fragment.app.Fragment
import com.base_library.base.DataBindingConfig
import com.base_library.base.LazyVmFragment
import com.jetpack.R

/**
 * A simple [Fragment] subclass.
 */
class HistoryFragment : LazyVmFragment() {

    override fun lazyInit() {
    }

    override fun getLayoutId() = R.layout.fragment_history
    override fun getDataBindingConfig(): DataBindingConfig? {
        return null
    }

}
