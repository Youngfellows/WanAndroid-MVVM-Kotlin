package com.shehuan.wanandroid.ui.tree.treeDetail

import com.shehuan.wanandroid.base.net.BaseRepository
import com.shehuan.wanandroid.base.net.RetrofitManager

/**
 * 体系Repository
 */
class TreeDetailRepository : BaseRepository() {

    /**
     * 获取体系下的文章
     * @param pageNum 分页
     * @param cid 体系ID
     */
    suspend fun getTreeDetail(pageNum: Int, cid: Int) =
        executeRequest { RetrofitManager.getApis().treeDetail(pageNum, cid) }

    /**
     * 收藏
     * @param id 文章ID
     */
    suspend fun collectArticle(id: Int) =
        executeRequest { RetrofitManager.getApis().collectArticle(id) }

    /**
     * TODO取消收藏
     *
     * @param id 文章ID
     */
    suspend fun uncollectArticle(id: Int) =
        executeRequest { RetrofitManager.getApis().uncollectArticle(id) }
}