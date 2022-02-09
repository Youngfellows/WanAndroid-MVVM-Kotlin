package com.shehuan.wanandroid.ui.project

import androidx.lifecycle.MutableLiveData
import com.shehuan.wanandroid.base.BaseViewModel
import com.shehuan.wanandroid.base.net.exception.ApiException
import com.shehuan.wanandroid.bean.ProjectCategoryBean

/**
 * 项目分类
 * @property repository 具体执行任务的repository
 */
class ProjectViewModel(private val repository: ProjectRepository) : BaseViewModel() {

    /**
     * 项目分类列表
     */
    var categoryList = MutableLiveData<List<ProjectCategoryBean>>()

    /**
     * 网络异常
     */
    var categoryListFail = MutableLiveData<ApiException>()

    /**
     * 获取项目分类列表
     */
    fun getCategoryList() {
        launch({
            categoryList.value = repository.getCategoryList()
        }, {
            categoryListFail.value = it
        })
    }
}