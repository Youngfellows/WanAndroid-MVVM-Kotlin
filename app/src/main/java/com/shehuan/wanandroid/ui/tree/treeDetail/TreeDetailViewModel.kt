package com.shehuan.wanandroid.ui.tree.treeDetail

import androidx.lifecycle.MutableLiveData
import com.shehuan.wanandroid.base.BaseViewModel
import com.shehuan.wanandroid.base.net.exception.ApiException
import com.shehuan.wanandroid.bean.treeDetail.TreeDetailBean

/**
 * 体系详情的ViewModel
 * @property repository 具体执行任务的repository
 */
class TreeDetailViewModel(private val repository: TreeDetailRepository) : BaseViewModel() {

    /**
     * 体系详情列表
     */
    var treeDetail = MutableLiveData<TreeDetailBean>()

    /**
     * 网络异常
     */
    var treeDetailFail = MutableLiveData<ApiException>()

    /**
     * 是否收藏成功
     */
    var collectSuccess = MutableLiveData<Boolean>()

    /**
     * 是否取消收藏成功
     */
    var uncollectSuccess = MutableLiveData<Boolean>()

    /**
     * 获取体系下的文章
     * @param pageNum
     * @param cid
     */
    fun getTreeDetail(pageNum: Int, cid: Int) {
        launch({
            treeDetail.value = repository.getTreeDetail(pageNum, cid)
        }, {
            treeDetailFail.value = it
        })
    }

    /**
     * 收藏文章
     * @param id
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
     * @param id
     */
    fun uncollectArticle(id: Int) {
        launch({
            repository.uncollectArticle(id)
            uncollectSuccess.value = true
        }, {
            uncollectSuccess.value = false
        })
    }
}