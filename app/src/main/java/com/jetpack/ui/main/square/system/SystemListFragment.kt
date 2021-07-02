package com.jetpack.ui.main.square.system

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.SimpleItemAnimator
import com.base_library.base.DataBindingConfig
import com.base_library.common.setNoRepeatClick
import com.base_library.utils.Param
import com.base_wa_lib.base.BaseLoadingFragment
import com.jetpack.R
import com.jetpack.common.ArticleAdapter
import com.jetpack.utils.CacheUtil
import kotlinx.android.synthetic.main.fragment_system_list.*
import kotlinx.android.synthetic.main.fragment_system_list.ivBack

/**
 *   /7/10
 *  
 */
class SystemListFragment : BaseLoadingFragment() {

    /**
     * 文章适配器
     */
    private lateinit var adapter:ArticleAdapter
    private lateinit var systemVM: SystemVM

    @Param
    private var systemId = 0

    @Param
    private var systemTitle = ""

    override fun initViewModel() {
        systemVM = getFragmentViewModel(SystemVM::class.java)
    }

    override fun observe() {
        systemVM.articleLiveData.observe(this, Observer {
            gloding?.dismiss()
            adapter.submitList(it)
        })
        systemVM.errorLiveData.observe(this, Observer {
            if (it.errorCode == -100) {
                //显示网络错误
                gloding?.showInternetError()
                gloding?.setReloadListener {
                    systemVM.getArticleList(systemId)
                }
            }
        })
    }

    override fun init(savedInstanceState: Bundle?) {
        initView()
        loadData()
    }

    override fun initView() {
        adapter = ArticleAdapter(mActivity).apply {
            rvSystemList.adapter = this

            setOnItemClickListener { i, _ ->
                nav().navigate(
                    R.id.action_system_list_fragment_to_web_fragment,
                    this@SystemListFragment.adapter.getBundle(i)
                )
            }
            setOnItemChildClickListener { i, view ->
                when (view.id) {
                    //收藏
                    R.id.ivCollect -> {
                        if (CacheUtil.isLogin()) {
                            this@SystemListFragment.adapter.currentList[i].apply {
                                //已收藏取消收藏
                                if (collect) {
                                    systemVM.unCollect(id)
                                } else {
                                    systemVM.collect(id)
                                }
                            }
                        } else {
                            nav().navigate(R.id.action_system_list_fragment_to_login_fragment)
                        }
                    }
                }

            }
        }
        //关闭更新动画
        (rvSystemList.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        tvTitle.text = systemTitle
        //配置smartRefresh

        setNoRepeatClick(ivBack){
            when(it.id){
                R.id.ivBack -> nav().navigateUp()
            }
        }
    }

    override fun loadData() {
        //自动刷新
        systemVM.getArticleList(systemId)
        gloding?.loading()
    }

    override fun getLayoutId() = R.layout.fragment_system_list
    override fun getDataBindingConfig(): DataBindingConfig? {
        return null
    }
}