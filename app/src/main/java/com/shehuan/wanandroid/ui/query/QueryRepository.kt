package com.shehuan.wanandroid.ui.query

import com.shehuan.wanandroid.base.net.BaseRepository
import com.shehuan.wanandroid.base.net.RetrofitManager

/**
 * 查询搜索的Repository
 */
class QueryRepository : BaseRepository() {

    /**
     * 收藏文章
     * @param id
     */
    suspend fun collectArticle(id: Int) =
        executeRequest { RetrofitManager.getApis().collectArticle(id) }

    /**
     * 取消收藏文章
     * @param id
     */
    suspend fun uncollectArticle(id: Int) =
        executeRequest { RetrofitManager.getApis().uncollectArticle(id) }

    /**
     * 获取热门搜索
     */
    suspend fun getHotKey() =
        executeRequest { RetrofitManager.getApis().hotKey() }

    /**
     * 搜索（支持多个关键词，用空格隔开）
     * @param pageNum 分页
     * @param k 关键字
     */
    suspend fun query(pageNum: Int, k: String) =
        executeRequest { RetrofitManager.getApis().query(pageNum, k) }

}