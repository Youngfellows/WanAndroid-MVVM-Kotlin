package com.shehuan.wanandroid.ui.collection

import androidx.lifecycle.MutableLiveData
import com.shehuan.wanandroid.base.BaseViewModel
import com.shehuan.wanandroid.base.net.exception.ApiException
import com.shehuan.wanandroid.bean.article.ArticleBean

/**
 * 文章收藏的ViewModel
 * @property repository 具体执行任务的repository
 */
class MyCollectionViewModel(private val repository: MyCollectionRepository) : BaseViewModel() {

    /**
     * 取消收藏是否成功
     */
    var cancelSuccess = MutableLiveData<Boolean>()

    /**
     * 收藏列表数据
     */
    var collectionList = MutableLiveData<ArticleBean>()

    /**
     * 网络异常
     */
    var collectionListFail = MutableLiveData<ApiException>()

    /**
     * 取消收藏
     * @param id 文章ID
     * @param originId
     */
    fun cancelCollection(id: Int, originId: Int) {
        launch({
            repository.cancelCollection(id, originId)
            cancelSuccess.value = true
        }, {
            cancelSuccess.value = false
        })
    }

    fun getCollectionList(pageNum: Int) {
        launch({
            collectionList.value = repository.getCollectionList(pageNum)
        }, {
            collectionListFail.value = it
        })
    }
}