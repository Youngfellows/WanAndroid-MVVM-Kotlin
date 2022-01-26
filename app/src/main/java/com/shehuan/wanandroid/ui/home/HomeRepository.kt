package com.shehuan.wanandroid.ui.home

import com.shehuan.wanandroid.base.net.BaseRepository
import com.shehuan.wanandroid.base.net.RetrofitManager

/**
 * 首页的Repository
 */
class HomeRepository : BaseRepository() {

    /**
     * 收藏站内文章
     * @param id
     */
    suspend fun collectArticle(id: Int) =
        executeRequest { RetrofitManager.getApis().collectArticle(id) }

    /**
     * 在文章列表取消收藏
     * @param id
     */
    suspend fun uncollectArticle(id: Int) =
        executeRequest { RetrofitManager.getApis().uncollectArticle(id) }

    /**
     * 首页banner
     */
    suspend fun getBannerList() =
        executeRequest { RetrofitManager.getApis().banner() }

    /**
     *  首页文章列表
     * @param pageNum
     */
    suspend fun getArticleList(pageNum: Int) =
        executeRequest { RetrofitManager.getApis().articleList(pageNum) }

}