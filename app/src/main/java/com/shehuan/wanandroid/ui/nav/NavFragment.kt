package com.shehuan.wanandroid.ui.nav

import androidx.lifecycle.Observer
import com.shehuan.wanandroid.R
import com.shehuan.wanandroid.base.fragment.BaseFragment
import com.shehuan.wanandroid.bean.navi.ArticlesItem
import com.shehuan.wanandroid.ui.website.HotWebsiteFragment
import com.shehuan.wanandroid.base.initViewModel
import com.shehuan.wanandroid.widget.VerticalTabLayout
import kotlinx.android.synthetic.main.fragment_navi.*

/**
 * 导航页
 */
class NavFragment : BaseFragment() {

    private val viewModel by lazy {
        initViewModel(
            this, NavViewModel::class, NavRepository::class
        )
    }

    /**
     * Fragment页面集合
     */
    private val fragments: ArrayList<BaseFragment> = arrayListOf()

    companion object {
        /**
         * 静态方法
         */
        fun newInstance() = NavFragment()
    }

    override fun initLoad() {
        statusView.showLoadingView()
        viewModel.getNavList()
    }

    /**
     * 加载布局资源
     * @return
     */
    override fun initLayoutResID(): Int {
        return R.layout.fragment_navi
    }

    override fun initData() {
        viewModel.navList.observe(this, Observer {
            statusView.showContentView()
            val tabNames = arrayListOf<String>()
            tabNames.add(getString(R.string.hot_website))
            fragments.add(HotWebsiteFragment.newInstance())
            for (navBean in it) {
                tabNames.add(navBean.name)
                fragments.add(NavDetailFragment.newInstance(navBean.articles as ArrayList<ArticlesItem>))
            }
            naviTabLayout.addTabs(tabNames)
            initDetailFragment()
        })
    }

    override fun initView() {
        //设置tab点击事件
        naviTabLayout.setOnTabClickListener(object : VerticalTabLayout.OnTabClickListener {
            override fun onTabClick(oldTabIndex: Int, newTabIndex: Int) {
                //切换页面
                fragmentManager?.beginTransaction()
                    ?.hide(fragments[oldTabIndex])
                    ?.show(fragments[newTabIndex])
                    ?.commit()
            }
        })

        initStatusView(navRootLayout) {
            initLoad()
        }
    }

    private fun initDetailFragment() {
        val transition = fragmentManager!!.beginTransaction()
        for (f in fragments) {
            transition.add(R.id.naviDetailContainer, f).hide(f)
        }
        transition.show(fragments[0])
        transition.commit()
    }
}
