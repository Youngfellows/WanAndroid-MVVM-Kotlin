package com.shehuan.wanandroid.ui.tree

import com.shehuan.wanandroid.base.net.BaseRepository
import com.shehuan.wanandroid.base.net.RetrofitManager

class TreeRepository : BaseRepository() {
    /**
     * 获取体系结构
     */
    suspend fun getTree() =
        executeRequest { RetrofitManager.getApis().tree() }
}