package com.shehuan.wanandroid.ui.main

import com.shehuan.wanandroid.base.net.BaseRepository
import com.shehuan.wanandroid.base.net.RetrofitManager

/**
 * 主存储库
 */
class MainRepository : BaseRepository() {
    /**
     * 退出
     */
    suspend fun logout() = RetrofitManager.getApis().logout()
}