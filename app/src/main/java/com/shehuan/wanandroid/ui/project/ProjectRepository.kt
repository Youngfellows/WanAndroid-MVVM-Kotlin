package com.shehuan.wanandroid.ui.project

import com.shehuan.wanandroid.base.net.BaseRepository
import com.shehuan.wanandroid.base.net.RetrofitManager

class ProjectRepository : BaseRepository() {

    /**
     * 获取项目分类
     */
    suspend fun getCategoryList() =
        executeRequest { RetrofitManager.getApis().projectCategory() }
}