package com.shehuan.wanandroid.base

import android.app.Activity
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


/**
 * 在Activity初始化DataBinding
 */
fun <VDB : ViewDataBinding> initDataBinding(activity: Activity, @LayoutRes layoutId: Int): VDB =
    DataBindingUtil.setContentView<VDB>(activity, layoutId)


/**
 * 在Fragment、Adapter初始化DataBinding
 */
fun <VDB : ViewDataBinding> initDataBinding(view: View): VDB = DataBindingUtil.bind<VDB>(view)!!