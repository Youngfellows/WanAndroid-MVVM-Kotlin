package com.shehuan.wanandroid.utils

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.flexbox.FlexboxLayout
import com.shehuan.wanandroid.R
import com.shehuan.wanandroid.bean.tree.ChildrenItem

/**
 * 加载图片资源
 * @param url 图片资源连接
 */
@BindingAdapter("bind:loadImage")
fun ImageView.loadImage(url: String?) {
    if (url != null) ImageLoader.load(context, url, this)
}

/**
 * 动态加载view布局
 * @param data 数据集
 */
@BindingAdapter("bind:initFlexbox")
fun FlexboxLayout.initFlexbox(data: List<ChildrenItem>) {
    this.removeAllViews()
    for (tree in data) {
        val view = TextView(context)
        view.text = tree.name
        view.setTextColor(context.resources.getColor(R.color.c8A8A8A))
        val params = FlexboxLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val margin1 = CommonUtil.dp2px(context, 3)
        val margin2 = CommonUtil.dp2px(context, 15)
        params.setMargins(0, margin1, margin2, margin1)
        this.addView(view, params)
    }
}