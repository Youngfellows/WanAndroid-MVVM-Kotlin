package com.shehuan.wanandroid.ui.tree

import androidx.lifecycle.MutableLiveData
import com.shehuan.wanandroid.base.BaseViewModel
import com.shehuan.wanandroid.base.net.exception.ApiException
import com.shehuan.wanandroid.bean.tree.TreeBean

/**
 * 体系的ViewModel
 * @property repository 具体执行任务的repository
 */
class TreeViewModel(private val repository: TreeRepository) : BaseViewModel() {

    /**
     * 体系列表
     */
    var treeList = MutableLiveData<List<TreeBean>>()

    /**
     * 网络异常
     */
    var treeFail = MutableLiveData<ApiException>()

    /**
     * 获取体系列表
     */
    fun getTree() {
        launch({
            treeList.value = repository.getTree()
        }, {
            treeFail.value = it
        })
    }
}