package com.shehuan.wanandroid.ui.project.projectDetail

import com.shehuan.wanandroid.base.net.BaseRepository
import com.shehuan.wanandroid.base.net.RetrofitManager

/**
 * 项目详情的Repository
 */
class ProjectDetailRepository : BaseRepository() {
    /**
     * 收藏文章
     * @param id 文章ID
     */
    suspend fun collectArticle(id: Int) =
        executeRequest { RetrofitManager.getApis().collectArticle(id) }

    /**
     * 取消收藏文章
     * @param id 文章ID
     */
    suspend fun uncollectArticle(id: Int) =
        executeRequest { RetrofitManager.getApis().uncollectArticle(id) }

    /**
     * 获取最新项目
     * @param pageNum 分页
     */
    suspend fun getNewProjectList(pageNum: Int) =
        executeRequest { RetrofitManager.getApis().newProject(pageNum) }

    /**
     * 项目分类详情列表
     *
     * @param pageNum 分页
     * @param cid 项目分类ID
     */
    suspend fun getProjectList(pageNum: Int, cid: Int) =
        executeRequest { RetrofitManager.getApis().projectDetail(pageNum, cid) }

}