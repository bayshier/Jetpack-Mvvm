package com.jetpack.ui.main.home

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.SimpleItemAnimator
import com.base_library.base.DataBindingConfig
import com.base_library.common.*
import com.base_wa_lib.base.BaseLazyLoadingFragment
import com.jetpack.BR
import com.jetpack.R
import com.jetpack.common.ArticleAdapter
import com.jetpack.utils.CacheUtil
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * des 首页
 *  
 *   -05-14
 */
class HomeFragment : BaseLazyLoadingFragment(){

    private var homeVm: HomeVM? = null
    private var bannerList: MutableList<BannerBean>? = null
    private val adapter by lazy { ArticleAdapter(mActivity) }


    override fun initViewModel() {
        homeVm = getActivityViewModel(HomeVM::class.java)
    }

    override fun observe() {
        //文章列表
        homeVm?.articleList?.observe(this, Observer {
            adapter.submitList(it)
            loadingTip?.dismiss()
        })
        //banner
        homeVm?.banner?.observe(this, Observer {
            bannerList = it
            initBanner()
        })
        //请求错误
        homeVm?.errorLiveData?.observe(this, Observer {
        })
    }

    override fun lazyInit() {
        initView()
        loadData()
    }

    override fun initView() {
        //关闭更新动画
        (rvHomeList.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        adapter.apply {
            rvHomeList.adapter = this
            setOnItemClickListener { i, _ ->
                nav().navigate(
                    R.id.action_main_fragment_to_web_fragment,
                    this@HomeFragment.adapter.getBundle(i)
                )
            }
            setOnItemChildClickListener { i, view ->
                when (view.id) {
                    //收藏
                    R.id.ivCollect -> {
                        if (CacheUtil.isLogin()) {
                            this@HomeFragment.adapter.currentList[i].apply {
                                //已收藏取消收藏
                                if (collect) {
                                    homeVm?.unCollect(id)
                                } else {
                                    homeVm?.collect(id)
                                }
                            }
                        } else {
                            nav().navigate(R.id.action_main_fragment_to_login_fragment)
                        }
                    }
                }
            }
        }
        setNoRepeatClick(ivAdd) {
            when (it.id) {
                R.id.ivAdd -> nav().navigate(R.id.action_main_fragment_to_publish_fragment)
            }
        }
    }

    override fun loadData() {
        //自动刷新
        homeVm?.getBanner()
        homeVm?.getArticle()
        loadingTip?.loading()
    }

    override fun onClick() {
        setNoRepeatClick(clSearch) {
            when (it.id) {
                R.id.clSearch -> nav().navigate(R.id.action_main_fragment_to_search_fragment)
            }
        }
    }

    override fun getLayoutId() = R.layout.fragment_home

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_home, homeVm)
            .addBindingParam(BR.vm, homeVm)
    }

    /**
     * 初始化banner
     */
    private fun initBanner() {

    }

}
