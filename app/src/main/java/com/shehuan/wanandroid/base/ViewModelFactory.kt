package com.shehuan.wanandroid.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.shehuan.wanandroid.base.net.BaseRepository
import kotlin.reflect.KClass

/**
 * 在Activity中初始化viewModel,把ViewModel和Repository进行关联
 */
@Suppress("UNCHECKED_CAST")
fun <BVM : BaseViewModel> initViewModel(
    activity: FragmentActivity,
    vmClass: KClass<BVM>,
    rClass: KClass<out BaseRepository>
): BVM {
    return ViewModelProviders.of(activity, object : ViewModelProvider.NewInstanceFactory() {
        override fun <VM : ViewModel> create(modelClass: Class<VM>): VM {
            //通过factory工厂,把ViewModel和Repository进行关联,反射调用
            //MainViewModel(private val repository: MainRepository)
            return vmClass.java.getConstructor(rClass.java)
                .newInstance(rClass.java.newInstance()) as VM
        }
    }).get(vmClass.java)
}

/**
 * 在Activity中初始化viewModel,把ViewModel和Repository进行关联
 */
/*@Suppress("UNCHECKED_CAST")
fun <BVM : BaseViewModel> initViewModel(
    activity: FragmentActivity,
    vmClass: KClass<BVM>,
    rClass: KClass<out BaseRepository>
) = ViewModelProviders.of(activity, object : ViewModelProvider.NewInstanceFactory() {
    override fun <VM : ViewModel> create(modelClass: Class<VM>): VM {
        //通过factory工厂,把ViewModel和Repository进行关联,反射调用
        //MainViewModel(private val repository: MainRepository)
        return vmClass.java.getConstructor(rClass.java)
            .newInstance(rClass.java.newInstance()) as VM
    }
}).get(vmClass.java)*/


/**
 * 在Fragment中初始化viewModel,把ViewModel和Repository进行关联
 */
@Suppress("UNCHECKED_CAST")
fun <BVM : BaseViewModel> initViewModel(
    fragment: Fragment,
    vmClass: KClass<BVM>,
    rClass: KClass<out BaseRepository>
) =
    ViewModelProviders.of(fragment, object : ViewModelProvider.NewInstanceFactory() {
        override fun <VM : ViewModel> create(modelClass: Class<VM>): VM {
            return vmClass.java.getConstructor(rClass.java)
                .newInstance(rClass.java.newInstance()) as VM
        }
    }).get(vmClass.java)