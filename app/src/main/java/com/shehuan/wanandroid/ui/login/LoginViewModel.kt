package com.shehuan.wanandroid.ui.login

import androidx.lifecycle.MutableLiveData
import com.shehuan.wanandroid.base.BaseViewModel
import com.shehuan.wanandroid.bean.LoginBean
import com.shehuan.wanandroid.bean.event.AccountEvent
import com.shehuan.wanandroid.utils.sp.SpUtil
import org.greenrobot.eventbus.EventBus

/**
 * 登录ViewModel
 * @property repository 具体执行登录的repository
 */
class LoginViewModel(private val repository: LoginRepository) : BaseViewModel() {

    /**
     * 登录响应数据
     */
    var loginBean = MutableLiveData<LoginBean>()

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     */
    fun login(username: String, password: String) {
        launch({
            loginBean.value = repository.login(username, password)
            SpUtil.setUsername(loginBean.value!!.username)
            EventBus.getDefault().post(AccountEvent())//发送消息,登录成功
        }, {
            loginBean.value = null
        })
    }
}