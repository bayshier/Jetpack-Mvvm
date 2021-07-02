package com.jetpack.ui.set

import android.os.Bundle
import androidx.lifecycle.Observer
import com.base_library.base.BaseVmFragment
import com.base_library.base.DataBindingConfig
import com.base_library.common.clickNoRepeat
import com.base_library.common.setNoRepeatClick
import com.base_library.common.toast
import com.base_library.utils.PrefUtils
import com.jetpack.BR
import com.jetpack.R
import com.jetpack.constants.Constants
import com.jetpack.constants.UrlConstants
import com.jetpack.utils.CacheUtil
import com.jetpack.view.DialogUtils
import kotlinx.android.synthetic.main.fragment_set.*

/**
 * des 设置
 *
 *   -06-30
 */
class SetFragment : BaseVmFragment() {

    private lateinit var setVM: SetVM

    override fun initViewModel() {
        setVM = getFragmentViewModel(SetVM::class.java)
    }

    override fun observe() {
        setVM.logoutLiveData.observe(this, Observer {
            toast("已退出登陆")
            nav().navigateUp()
        })
    }

    override fun init(savedInstanceState: Bundle?) {
        setNightMode()
    }

    /**
     * 却换夜间/白天模式
     */
    private fun setNightMode() {
        val theme = PrefUtils.getBoolean(Constants.SP_THEME_KEY,false)
        scDayNight.isChecked = theme
        //不能用切换监听,否则会递归
        scDayNight.clickNoRepeat {
            it.isSelected = !theme
            PrefUtils.setBoolean(Constants.SP_THEME_KEY, it.isSelected)
            mActivity.recreate()
        }
    }

    override fun onClick() {
        setNoRepeatClick(ivBack, tvClear, tvVersion, tvAuthor, tvProject, tvCopyright, tvLogout) {
            when (it.id) {
                R.id.ivBack -> nav().navigateUp()
                R.id.tvClear -> {

                }
                R.id.tvVersion -> {

                }
                R.id.tvAuthor -> {
                }
                R.id.tvProject -> {
                    nav().navigate(R.id.action_set_fragment_to_web_fragment, Bundle().apply {
                        putString(Constants.WEB_URL, UrlConstants.APP_GITHUB)
                        putString(Constants.WEB_TITLE, Constants.APP_NAME)
                    })
                }
                R.id.tvCopyright -> {
                }
                R.id.tvLogout -> {
                    if (!CacheUtil.isLogin()){
                        toast("请先登陆～")
                        return@setNoRepeatClick
                    }
                    DialogUtils.confirm(mActivity,"确定退出登录？"){
                        setVM.logout()
                    }
                }
            }
        }
    }


    override fun getLayoutId() = R.layout.fragment_set
    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_set, setVM)
            .addBindingParam(BR.vm, setVM)
    }

}