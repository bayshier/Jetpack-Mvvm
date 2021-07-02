package com.jetpack.ui.my

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.base_library.base.BaseVmFragment
import com.base_library.base.DataBindingConfig
import com.base_library.common.setNoRepeatClick
import com.base_library.common.toast
import com.jetpack.R
import com.jetpack.common.OnChildItemClickListener
import com.jetpack.constants.Constants
import com.jetpack.utils.CacheUtil
import com.base_wa_lib.view.LoadingTip
import kotlinx.android.synthetic.main.fragment_my_article.*

/**
 * des 我的文章
 *  
 * @data 2020/7/12
 */
class MyArticleFragment : BaseVmFragment(), OnChildItemClickListener {

    private val adapter by lazy { MyArticleAdapter() }
    private lateinit var myVM: MyArticleVM

    private val loadingView by lazy {
        LoadingTip(
            mActivity
        )
    }

    override fun initViewModel() {
        myVM = getFragmentViewModel(MyArticleVM::class.java)
    }

    override fun init(savedInstanceState: Bundle?) {
        initView()
        loadData()
    }

    override fun initView() {
        adapter.apply {
            setOnChildItemClickListener(this@MyArticleFragment)
            emptyView = loadingView
            rvMyArticleList.adapter = this
        }

    }

    override fun onClick() {
        setNoRepeatClick(ivBack, ivAdd) {
            when (it.id) {
                R.id.ivBack -> nav().navigateUp()
                R.id.ivAdd -> {
                    if (CacheUtil.isLogin()) {
                        nav().navigate(R.id.action_my_article_fragment_to_publish_fragment)
                    } else {
                        toast("请先登录～")
                    }
                }
            }
        }
    }

    override fun observe() {
        myVM.myLiveDate.observe(this, Observer {
            adapter.setNewData(it)
        })
        myVM.deleteLiveData.observe(this, Observer {
            adapter.deleteById(it)
        })
        myVM.emptyLiveDate.observe(this, Observer {
            loadingView.showEmpty()
        })
        myVM.errorLiveData.observe(this, Observer {
        })
    }

    override fun loadData() {
    }

    override fun getLayoutId() = R.layout.fragment_my_article

    override fun getDataBindingConfig(): DataBindingConfig? {
        return null
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        this.adapter.data.apply {
            when(view.id){
                R.id.rlContent->{
                    nav().navigate(R.id.action_my_article_fragment_to_web_fragment,Bundle().apply {
                        putString(Constants.WEB_URL,get(position).link)
                        putString(Constants.WEB_TITLE,get(position).title)
                    })
                }
                R.id.tvDelete->{
                    if (position<size){
                        myVM.delete(get(position).id)
                    }
                }
            }
        }
    }
}