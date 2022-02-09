package com.shehuan.wanandroid.ui.query

import androidx.lifecycle.MutableLiveData
import com.shehuan.wanandroid.base.BaseViewModel
import com.shehuan.wanandroid.base.net.exception.ApiException
import com.shehuan.wanandroid.bean.HotKeyBean
import com.shehuan.wanandroid.bean.article.ArticleBean

/**
 * 搜索的ViewModel
 * @property repository
 */
class QueryViewModel(private val repository: QueryRepository) : BaseViewModel() {

    /**
     * 收藏文章是否成功
     */
    var collectSuccess = MutableLiveData<Boolean>()

    /**
     * 取消收藏文章是否成功
     */
    var uncollectSuccess = MutableLiveData<Boolean>()


    var hotKeyList = MutableLiveData<List<HotKeyBean>>()

    /**
     * 查询结果列表
     */
    var queryList = MutableLiveData<ArticleBean>()

    /**
     * 查询结果异常
     */
    var queryListFail = MutableLiveData<ApiException>()

    /**
     * 收藏文章
     * @param id 文章ID
     */
    fun collectArticle(id: Int) {
        launch({
            repository.collectArticle(id)
            collectSuccess.value = true
        }, {
            collectSuccess.value = false
        })
    }

    /**
     * 取消收藏文章
     * @param id 文章ID
     */
    fun uncollectArticle(id: Int) {
        launch({
            repository.uncollectArticle(id)
            uncollectSuccess.value = true
        }, {
            uncollectSuccess.value = false
        })
    }

    /**
     * 获取热门搜索
     */
    fun getHotKey() {
        launch({
            hotKeyList.value = repository.getHotKey()
        }, {

        })
    }


    /**
     * 搜索（支持多个关键词，用空格隔开）
     * @param pageNum 分页
     * @param k 关键字
     */
    fun query(pageNum: Int, k: String) {
        launch({
            queryList.value = repository.query(pageNum, k)
        }, {
            queryListFail.value = it
        })
    }
}