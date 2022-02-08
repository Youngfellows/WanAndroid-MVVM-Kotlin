package com.shehuan.wanandroid.ui.register

import androidx.lifecycle.MutableLiveData
import com.shehuan.wanandroid.base.BaseViewModel
import com.shehuan.wanandroid.bean.RegisterBean
import com.shehuan.wanandroid.bean.event.AccountEvent
import com.shehuan.wanandroid.utils.sp.SpUtil
import org.greenrobot.eventbus.EventBus

/**
 * 注册账号的ViewModel
 * @property repository 真正注册的repository
 */
class RegisterViewModel(private val repository: RegisterRepository) : BaseViewModel() {

    /**
     * 注册账号响应
     */
    var registerBean = MutableLiveData<RegisterBean>()


    /**
     * 注册账号
     * @param username 用户名
     * @param password 密码
     * @param repassword 密码
     */
    fun register(username: String, password: String, repassword: String) {
        launch({
            registerBean.value = repository.register(username, password, repassword)
            SpUtil.setUsername(registerBean.value!!.username)
            EventBus.getDefault().post(AccountEvent())
        }, {
            registerBean.value = null
        })
    }
}