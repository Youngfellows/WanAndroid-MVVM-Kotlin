package com.shehuan.wanandroid.ui.chapter

import com.shehuan.wanandroid.base.net.BaseRepository
import com.shehuan.wanandroid.base.net.RetrofitManager


class ChapterRepository : BaseRepository() {

    /**
     * 获取公众号列表
     */
    suspend fun getChapterList() =
        executeRequest { RetrofitManager.getApis().chapter() }

}