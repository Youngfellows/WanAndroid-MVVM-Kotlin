package com.shehuan.wanandroid.ui.home

import androidx.lifecycle.MutableLiveData
import com.shehuan.wanandroid.base.BaseViewModel
import com.shehuan.wanandroid.base.net.exception.ApiException
import com.shehuan.wanandroid.bean.BannerBean
import com.shehuan.wanandroid.bean.article.ArticleBean

/**
 * 首页 ViewModel
 * @property repository 具体请求任务
 */
class HomeViewModel(private val repository: HomeRepository) : BaseViewModel() {

    /**
     * 是否收藏成功
     */
    var collectSuccess = MutableLiveData<Boolean>()

    /**
     * 是否取消收藏成功
     */
    var uncollectSuccess = MutableLiveData<Boolean>()

    /**
     * 轮播图列表
     */
    var bannerList = MutableLiveData<List<BannerBean>>()

    /**
     * 文章列表
     */
    var articleList = MutableLiveData<ArticleBean>()

    /**
     * 网络请求异常
     */
    var articleListFail = MutableLiveData<ApiException>()

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
     * 在文章列表取消收藏
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
     * 获取首页banner
     */
    fun getBannerList() {
        launch({
            bannerList.value = repository.getBannerList()
        }, {

        })
    }

    /**
     * 文章列表
     * @param pageNum 第几页
     */
    fun getArticleList(pageNum: Int) {
        launch({
            articleList.value = repository.getArticleList(pageNum)
        }, {
            articleListFail.value = it
        })
    }
}