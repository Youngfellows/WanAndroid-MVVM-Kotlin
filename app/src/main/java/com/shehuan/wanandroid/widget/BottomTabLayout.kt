package com.shehuan.wanandroid.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.shehuan.wanandroid.R

/**
 * 底部导航控件
 */
class BottomTabLayout : LinearLayout {

    /**
     * 当前选中索引
     */
    private var currentTabIndex = 0

    /**
     * 轮播图
     */
    private lateinit var viewPager: ViewPager

    /**
     * 次级构造函数
     */
    constructor(context: Context) : this(context, null)

    /**
     * 次级构造函数
     */
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    /**
     * 次级构造函数
     */
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        //水平布局
        orientation = HORIZONTAL
    }

    /**
     * 添加一个导航控件到底部控件
     * @param tabName
     * @param defaultIconId
     * @param selectIconId
     */
    fun addTab(tabName: String, defaultIconId: Int, selectIconId: Int) {
        val tab = TabItem(context, tabName, defaultIconId, selectIconId)
        //绑定索引
        tab.tag = childCount
        tab.setOnClickListener {
            val tag: Int = it.tag as Int
            if (tag != currentTabIndex) {
                //点击联动
                viewPager.setCurrentItem(tag, true)
            }
        }

        if (childCount == 0)
            tab.select()

        val params = LayoutParams(0, LayoutParams.MATCH_PARENT)
        params.weight = 1f

        addView(tab, params)
    }

    /**
     * 绑定轮播图到导航控件
     * @param viewPager
     */
    fun setupWithViewPager(viewPager: ViewPager) {
        this.viewPager = viewPager
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(i: Int) {

            }

            override fun onPageScrolled(i: Int, v: Float, i1: Int) {

            }

            override fun onPageSelected(i: Int) {
                if (i != currentTabIndex)
                    switchTab(i)
            }
        })
    }

    /**
     * 轮播切换
     * @param destTabIndex
     */
    fun switchTab(destTabIndex: Int) {
        val destTab: TabItem = getChildAt(destTabIndex) as TabItem
        destTab.select()
        listener.onTabSwitch(destTabIndex, destTab.tabName())
        val currentTab: TabItem = getChildAt(currentTabIndex) as TabItem
        currentTab.unSelect()
        currentTabIndex = destTabIndex
    }

    /**
     * tab切换监听
     */
    private lateinit var listener: OnTabSwitchListener

    fun setOnTabSwitchListener(listener: OnTabSwitchListener) {
        this.listener = listener
    }

    interface OnTabSwitchListener {
        fun onTabSwitch(tabIndex: Int, tabName: String)
    }

    /**
     * 导航控件子控件
     * @property defaultIconId 默认图片资源ID
     * @property selectIconId 选中图片资源ID
     * @constructor
     * TODO
     *
     * @param context
     * @param nameStr 描述
     */
    @SuppressLint("ViewConstructor")
    private class TabItem(
        context: Context?,
        nameStr: String,
        private val defaultIconId: Int,
        private val selectIconId: Int
    ) : LinearLayout(context) {
        /**
         * 图片
         */
        private val iconIv: ImageView

        /**
         * 文字
         */
        private val nameTv: TextView

        init {
            orientation = VERTICAL
            val tab: LinearLayout = inflate(R.layout.tab_item_bottom_layout) as LinearLayout
            iconIv = tab.findViewById(R.id.tabIcon)
            iconIv.setImageResource(defaultIconId)
            nameTv = tab.findViewById(R.id.tabName)
            nameTv.text = nameStr
        }

        /**
         * 选中效果
         */
        fun select() {
            iconIv.setImageResource(selectIconId)
            nameTv.setTextColor(resources.getColor(R.color.cFE6243))
        }

        /**
         * 取消选中
         */
        fun unSelect() {
            iconIv.setImageResource(defaultIconId)
            nameTv.setTextColor(resources.getColor(R.color.c707070))
        }

        fun tabName() = nameTv.text.toString()

        /**
         * 加载tab切换控件
         * @param layoutId 布局资源
         * @return
         */
        private fun inflate(layoutId: Int): View =
            LayoutInflater.from(context).inflate(layoutId, this)

    }
}