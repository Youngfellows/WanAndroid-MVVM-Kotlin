package com.shehuan.wanandroid.ui.nav

import androidx.lifecycle.MutableLiveData
import com.shehuan.wanandroid.base.BaseViewModel
import com.shehuan.wanandroid.bean.navi.NaviBean

/**
 * 导航页ViewModel
 * @property repository 具体执行任务
 */
class NavViewModel(private val repository: NavRepository) : BaseViewModel() {

    /**
     * 导航列表
     */
    var navList = MutableLiveData<List<NaviBean>>()

    /**
     * 获取导航列表
     */
    fun getNavList() {
        launch({
            navList.value = repository.getNavList()
        }, {

        })
    }
}