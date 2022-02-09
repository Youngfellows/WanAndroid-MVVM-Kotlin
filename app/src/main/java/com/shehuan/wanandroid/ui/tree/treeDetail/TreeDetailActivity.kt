package com.shehuan.wanandroid.ui.tree.treeDetail

import android.content.Intent
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.shehuan.wanandroid.R
import com.shehuan.wanandroid.adapter.ViewPagerAdapter
import com.shehuan.wanandroid.base.activity.BaseActivity
import com.shehuan.wanandroid.bean.tree.ChildrenItem
import kotlinx.android.synthetic.main.activity_tree_detail.*

/**
 * 体系详情页
 */
class TreeDetailActivity : BaseActivity() {

    /**
     * Title标题
     */
    private lateinit var title: String

    /**
     * 二级体系
     */
    private lateinit var secondaryTree: ArrayList<ChildrenItem>

    companion object {
        /**
         * 静态方法,保存数据
         * @param context
         * @param title
         * @param secondaryTree
         */
        fun start(context: BaseActivity, title: String, secondaryTree: ArrayList<ChildrenItem>) {
            val intent = Intent(context, TreeDetailActivity::class.java)
            intent.apply {
                putExtra("title", title)
                putParcelableArrayListExtra("secondaryTree", secondaryTree)
            }
            context.startActivity(intent)
        }
    }

    override fun initContentView() {
        setContentView(R.layout.activity_tree_detail)
    }

    override fun initData() {
        intent?.let {
            title = it.getStringExtra("title")
            secondaryTree = it.getParcelableArrayListExtra("secondaryTree")
        }
    }

    override fun initView() {
        initToolbar(title)

        val titles = arrayListOf<String>()
        val fragments = arrayListOf<Fragment>()
        for (tree in secondaryTree) {
            titles.add(tree.name)
            fragments.add(TreeDetailFragment.newInstance(tree.id))
        }

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.setFragmentsAndTitles(fragments, titles)
        treeDetailViewPager.offscreenPageLimit = secondaryTree.size
        treeDetailViewPager.adapter = viewPagerAdapter
        if (titles.size > 3) {
            treeDetailTabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        }
        treeDetailTabLayout.setupWithViewPager(treeDetailViewPager)
    }

    override fun initLoad() {

    }
}
