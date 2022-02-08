package com.shehuan.wanandroid.ui.chapter.chapterDetail

import androidx.lifecycle.MutableLiveData
import com.shehuan.wanandroid.base.BaseViewModel
import com.shehuan.wanandroid.base.net.exception.ApiException
import com.shehuan.wanandroid.bean.chapter.ChapterArticleBean

/**
 * 公众号详情的ViewModel
 * @property repository 具体执行任务的repository
 */
class ChapterDetailViewModel(private val repository: ChapterDetailRepository) : BaseViewModel() {

    /**
     * 是否收藏成功
     */
    var collectSuccess = MutableLiveData<Boolean>()

    /**
     * 是否取消收藏成功
     */
    var uncollectSuccess = MutableLiveData<Boolean>()

    /**
     * 公众号列表
     */
    var articleBean = MutableLiveData<ChapterArticleBean>()

    /**
     * 网络异常
     */
    var articleBeanFail = MutableLiveData<ApiException>()

    /**
     * 查询公众号列表
     */
    var queryArticleBean = MutableLiveData<ChapterArticleBean>()

    /**
     * 查询网络异常
     */
    var queryArticleBeanFail = MutableLiveData<ApiException>()


    fun collectArticle(id: Int) {
        launch({
            repository.collectArticle(id)
            collectSuccess.value = true
        }, {
            collectSuccess.value = false
        })
    }

    fun uncollectArticle(id: Int) {
        launch({
            repository.uncollectArticle(id)
            uncollectSuccess.value = true
        }, {
            uncollectSuccess.value = false
        })
    }

    /**
     * 获取公众号列表
     * @param chapterId
     * @param pageNum
     */
    fun getChapterArticleList(chapterId: Int, pageNum: Int) {
        launch({
            articleBean.value = repository.getChapterArticleList(chapterId, pageNum)
        }, {
            articleBeanFail.value = it
        })
    }

    /**
     * 查询微信公众号文章列表
     * @param chapterId
     * @param pageNum
     * @param k
     */
    fun queryChapterArticle(chapterId: Int, pageNum: Int, k: String) {
        launch({
            queryArticleBean.value = repository.queryChapterArticle(chapterId, pageNum, k)
        }, {
            queryArticleBeanFail.value = it
        })
    }
}