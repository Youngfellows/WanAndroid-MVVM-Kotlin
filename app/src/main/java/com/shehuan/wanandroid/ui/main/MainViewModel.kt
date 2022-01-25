package com.shehuan.wanandroid.ui.main

import androidx.lifecycle.MutableLiveData
import com.shehuan.wanandroid.App
import com.shehuan.wanandroid.R
import com.shehuan.wanandroid.base.BaseViewModel
import com.shehuan.wanandroid.utils.ToastUtil
import com.shehuan.wanandroid.utils.sp.SpUtil

/**
 * @property repository 具体执行任务的repository
 */
class MainViewModel(private val repository: MainRepository) : BaseViewModel() {

    /**
     * 观察是否登出成功
     */
    var logoutSuccess = MutableLiveData<Boolean>()

    /**
     *退出
     */
    fun logout() {
        launch(request = {
            repository.logout()
            SpUtil.removeCookies()
            SpUtil.removeUsername()
            ToastUtil.show(App.getApp(), R.string.logout_tip)
            logoutSuccess.value = true
        }, fail = {
            logoutSuccess.value = false
        })
    }
}