package com.shehuan.wanandroid.ui.website

import com.shehuan.wanandroid.base.net.BaseRepository
import com.shehuan.wanandroid.base.net.RetrofitManager

/**
 * 常用网站Repository
 */
class HotWebsiteRepository : BaseRepository() {
    /**
     * 获取常用网站列表
     */
    suspend fun getFriendList() =
        executeRequest { RetrofitManager.getApis().friend() }
}