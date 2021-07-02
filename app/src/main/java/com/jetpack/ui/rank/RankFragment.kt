package com.jetpack.ui.rank

import android.os.Bundle
import androidx.lifecycle.Observer
import com.base_library.base.DataBindingConfig
import com.base_library.common.setNoRepeatClick
import com.base_library.utils.Param
import com.base_wa_lib.base.BaseLoadingFragment
import com.jetpack.BR
import com.jetpack.R
import com.jetpack.constants.Constants
import com.jetpack.constants.UrlConstants
import kotlinx.android.synthetic.main.fragment_rank.*
import kotlinx.android.synthetic.main.fragment_rank.tvIntegral
import kotlinx.android.synthetic.main.fragment_rank.tvRanking

/**
 * des 排名
 *   /7/13
 *  
 */
class RankFragment : BaseLoadingFragment() {

    /**
     * 我的积分
     */
    @Param
    private var myIntegral: Int? = null

    /**
     * 我的排名
     */
    @Param
    private var myRank: Int? = null

    /**
     * 我的名称
     */
    @Param
    private var myName: String? = null

    private val adapter by lazy { RankAdapter() }

    private lateinit var rankVM: RankVM

    override fun initViewModel() {
        rankVM = getFragmentViewModel(RankVM::class.java)
    }

    override fun observe() {
        rankVM.rankLiveData.observe(this, Observer {
            adapter.setNewData(it)
            gloding?.dismiss()
        })
        rankVM.errorLiveData.observe(this, Observer {
        })
    }

    override fun init(savedInstanceState: Bundle?) {
        initView()
        loadData()
    }

    override fun initView() {
        tvIntegral.text = "$myIntegral"
        tvRanking.text = "$myRank"

        rvRank.adapter = adapter

    }

    override fun loadData() {
        //自动刷新
        gloding?.loading()
    }

    override fun onClick() {
        setNoRepeatClick(ivBack, ivDetail) {
            when (it.id) {
                R.id.ivBack -> nav().navigateUp()
                //查看积分详情
                R.id.ivDetail -> {
                    nav().navigate(R.id.action_rank_fragment_to_web_fragment, Bundle().apply {
                        putString(Constants.WEB_URL, UrlConstants.INTEGRAL_RULE)
                        putString(Constants.WEB_TITLE, getString(R.string.integral_rule))
                    })
                }
            }
        }
    }

    override fun getLayoutId() = R.layout.fragment_rank

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_rank, rankVM)
            .addBindingParam(BR.vm, rankVM)
    }
}