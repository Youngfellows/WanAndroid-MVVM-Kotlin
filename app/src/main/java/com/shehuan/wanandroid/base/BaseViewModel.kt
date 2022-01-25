package com.shehuan.wanandroid.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shehuan.wanandroid.App
import com.shehuan.wanandroid.base.net.exception.ApiException
import com.shehuan.wanandroid.utils.ToastUtil
import kotlinx.coroutines.launch

/**
 *基础的ViewModel
 */
open class BaseViewModel : ViewModel() {

    /**
     *开启协程异步执行任务
     * @param request 请求挂起函数,也就是异步代码块,相当于回调
     * @param fail 挂起函数,也就是异步代码块,相当于回调,回调异常
     */
    protected fun launch(request: suspend () -> Unit, fail: suspend (ApiException) -> Unit) =
        viewModelScope.launch {
            try {
                request()
            } catch (e: ApiException) {
                ToastUtil.show(App.getApp(), e.errorMessage)
                fail(e)
            }
        }
}