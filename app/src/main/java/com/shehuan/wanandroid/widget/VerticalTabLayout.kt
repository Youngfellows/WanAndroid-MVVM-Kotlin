package com.shehuan.wanandroid.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.shehuan.wanandroid.R

/**
 * 竖直导航栏
 */
class VerticalTabLayout : LinearLayout {

    private var currentTabIndex = 0

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        orientation = VERTICAL
    }

    /**
     * 添加导航tab
     * @param tabNames 数据集
     */
    fun addTabs(tabNames: List<String>) {
        for (tabName in tabNames) {
            addTab(tabName)
        }
    }

    /**
     * 添加导航tab
     * @param tabName 显示内容
     */
    private fun addTab(tabName: String) {
        val tab = TabItem(context, tabName)
        tab.tag = childCount //tag是子控件个数
        tab.setOnClickListener {
            val tag: Int = it.tag as Int
            if (tag != currentTabIndex) {
                listener.onTabClick(currentTabIndex, tag)
                switchTab(tag)
            }
        }

        if (childCount == 0)
            tab.select()

        addView(tab)
    }

    /**
     * 切换tab
     * @param destTabIndex 要切换的tab索引
     */
    private fun switchTab(destTabIndex: Int) {
        val destTab: TabItem = getChildAt(destTabIndex) as TabItem
        destTab.select()
        val currentTab: TabItem = getChildAt(currentTabIndex) as TabItem
        currentTab.unSelect()
        currentTabIndex = destTabIndex
    }

    interface OnTabClickListener {

        /**
         * tab点击事件
         * @param oldTabIndex 旧tab位置
         * @param newTabIndex 新tab位置
         */
        fun onTabClick(oldTabIndex: Int, newTabIndex: Int)
    }

    /**
     * tab点击事件监听
     */
    lateinit var listener: OnTabClickListener

    fun setOnTabClickListener(listener: OnTabClickListener) {
        this.listener = listener
    }

    /**
     * 左边导航Tab
     * @constructor
     * TODO
     *
     * @param context 上下文
     * @param tabName 显示名称
     */
    @SuppressLint("ViewConstructor")
    private class TabItem(context: Context?, tabName: String) : LinearLayout(context) {
        private val indicatorView: View
        private val nameTv: TextView
        private val lineView: View

        init {
            //加载tab布局
            val tab: LinearLayout = inflate(R.layout.tab_item_nav_layout) as LinearLayout
            nameTv = tab.findViewById(R.id.nameTv)
            nameTv.text = tabName
            indicatorView = tab.findViewById(R.id.indicatorView)
            lineView = tab.findViewById(R.id.lineView)
        }

        /**
         * 选中tab
         */
        fun select() {
            nameTv.setTextColor(resources.getColor(R.color.cFE6243))
            indicatorView.visibility = View.VISIBLE
            lineView.visibility = View.GONE
        }

        /**
         * 未选中tab
         */
        fun unSelect() {
            nameTv.setTextColor(resources.getColor(R.color.c2C2C2C))
            indicatorView.visibility = View.INVISIBLE
            lineView.visibility = View.VISIBLE
        }

        /**
         * 加载tab布局
         * @param layoutId 布局资源
         * @return
         */
        private fun inflate(layoutId: Int): View =
            LayoutInflater.from(context).inflate(layoutId, this)
    }
}