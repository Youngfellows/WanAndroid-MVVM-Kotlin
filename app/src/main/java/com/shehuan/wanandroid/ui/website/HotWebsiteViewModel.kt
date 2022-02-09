package com.shehuan.wanandroid.ui.website

import androidx.lifecycle.MutableLiveData
import com.shehuan.wanandroid.base.BaseViewModel
import com.shehuan.wanandroid.base.net.exception.ApiException
import com.shehuan.wanandroid.bean.FriendBean

/**
 * 常用网站的ViewModel
 * @property repository 具体执行任务的repository
 */
class HotWebsiteViewModel(private val repository: HotWebsiteRepository) : BaseViewModel() {
    /**
     * 常用网站列表
     */
    var friendList = MutableLiveData<List<FriendBean>>()

    /**
     * 网络异常
     */
    var friendListFail = MutableLiveData<ApiException>()

    /**
     * 常用网站列表
     */
    fun getFriendList() {
        launch({
            friendList.value = repository.getFriendList()
        }, {
            friendListFail.value = it
        })
    }
}