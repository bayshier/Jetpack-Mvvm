package com.jetpack.ui.main.square


import androidx.fragment.app.Fragment
import com.base_library.base.DataBindingConfig
import com.base_library.base.LazyVmFragment
import com.base_library.common.initFragment
import com.jetpack.R
import com.jetpack.common.TabNavigatorAdapter
import com.jetpack.ui.main.square.system.SystemFragment
import com.jetpack.view.MagicIndicatorUtils
import kotlinx.android.synthetic.main.fragment_square.*
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter


/**
 * des 广场做成viewpager模式，以后有新功能可以在此处扩展。
 *  
 *   -05-14
 */
class SquareFragment : LazyVmFragment() {

    override fun lazyInit() {
        initView()
    }

    override fun initView() {
        mutableListOf<String>().apply {
            add("体系")
            add("导航")
            initViewPager(this)
        }
    }

    private fun initViewPager(tabList: MutableList<String>) {
        vpSquareFragment.initFragment(this, arrayListOf<Fragment>().apply {
            tabList.forEach { _ ->
                add(SystemFragment())
            }
        })
        //下划线绑定
        val commonNavigator = CommonNavigator(mActivity)
        commonNavigator.adapter = getCommonNavigatorAdapter(tabList)
        tabLayout.navigator = commonNavigator
        MagicIndicatorUtils.bindForViewPager2(vpSquareFragment, tabLayout)
    }

    /**
     * 获取下划线根跟字适配器
     */
    private fun getCommonNavigatorAdapter(tabList: MutableList<String>): CommonNavigatorAdapter {
        return TabNavigatorAdapter(tabList) {
            vpSquareFragment.currentItem = it
        }
    }


    override fun getLayoutId() = R.layout.fragment_square

    override fun getDataBindingConfig(): DataBindingConfig? {
        return null
    }


}

