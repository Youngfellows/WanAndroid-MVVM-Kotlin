package com.shehuan.wanandroid.ui.chapter.chapterDetail

import com.shehuan.wanandroid.base.net.BaseRepository
import com.shehuan.wanandroid.base.net.RetrofitManager


/**
 * 公众号详情的Repository
 */
class ChapterDetailRepository : BaseRepository() {

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
     * 获取微信公众号文章列表
     * @param chapterId 公众号ID
     * @param pageNum 分页数
     */
    suspend fun getChapterArticleList(chapterId: Int, pageNum: Int) =
        executeRequest { RetrofitManager.getApis().chapterArticleList(chapterId, pageNum) }

    /**
     * 微信公众号文章搜索
     * @param chapterId 公众号ID
     * @param pageNum 分页数
     * @param k 关键字
     */
    suspend fun queryChapterArticle(chapterId: Int, pageNum: Int, k: String) =
        executeRequest { RetrofitManager.getApis().queryChapterArticle(chapterId, pageNum, k) }
}