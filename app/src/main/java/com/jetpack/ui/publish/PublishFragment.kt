package com.jetpack.ui.publish

import android.os.Bundle
import androidx.lifecycle.Observer
import com.base_library.base.BaseVmFragment
import com.base_library.base.DataBindingConfig
import com.base_library.common.setNoRepeatClick
import com.base_library.common.toast
import com.jetpack.BR
import com.jetpack.R
import com.jetpack.view.DialogUtils
import kotlinx.android.synthetic.main.fragment_publish.*

/**
 * des 发布文章
 *  
 * @data 2020/7/12
 */
class PublishFragment : BaseVmFragment() {

    private lateinit var publishVM: PublishVM

    override fun initViewModel() {
        publishVM = getFragmentViewModel(PublishVM::class.java)
    }

    override fun observe() {
        publishVM.publishLiveData.observe(this, Observer {
            DialogUtils.dismiss()
            //结束当前界面
            nav().navigateUp()
            toast("发布成功")
        })
        publishVM.errorLiveData.observe(this, Observer {
            DialogUtils.dismiss()
        })
    }

    override fun init(savedInstanceState: Bundle?) {

    }

    override fun onClick() {
        setNoRepeatClick(ivBack, btPublish) {
            when (it.id) {
                R.id.ivBack -> nav().navigateUp()
                R.id.btPublish -> {
                    DialogUtils.showLoading(mActivity,"正在发布～")
                    publishVM.publish()
                }
            }
        }
    }

    override fun getLayoutId() = R.layout.fragment_publish

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_publish, publishVM)
            .addBindingParam(BR.vm, publishVM)
    }
}