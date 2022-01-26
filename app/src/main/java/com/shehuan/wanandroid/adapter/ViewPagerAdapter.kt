package com.shehuan.wanandroid.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter

/**
 *  为轮播图设置适配器
 * @constructor
 * TODO
 *
 * @param fragmentManager
 */
class ViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    /**
     * Fragment列表集
     */
    private lateinit var fragments: List<Fragment>

    /**
     * title集合
     */
    private lateinit var titles: List<String>

    fun setFragments(fragments: List<Fragment>) {
        this.fragments = fragments
    }

    fun setFragmentsAndTitles(fragments: List<Fragment>, titles: List<String>) {
        this.fragments = fragments
        this.titles = titles
    }

    /**
     * 根据索引获取Fragment指定页
     * @param position 位置
     * @return Fragment指定页
     */
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    /**
     * 获取Fragment总页数
     * @return 总页数
     */
    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

}