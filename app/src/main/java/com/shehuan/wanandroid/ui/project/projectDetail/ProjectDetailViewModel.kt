package com.shehuan.wanandroid.ui.project.projectDetail

import androidx.lifecycle.MutableLiveData
import com.shehuan.wanandroid.base.BaseViewModel
import com.shehuan.wanandroid.base.net.exception.ApiException
import com.shehuan.wanandroid.bean.ProjectCategoryBean
import com.shehuan.wanandroid.bean.project.ProjectBean

/**
 * 项目详情的ViewModel
 * @property repository 具体执行任务的repository
 */
class ProjectDetailViewModel(private val repository: ProjectDetailRepository) : BaseViewModel() {

    /**
     * 是否收藏成功
     */
    var collectSuccess = MutableLiveData<Boolean>()

    /**
     * 是否取消收藏成功
     */
    var uncollectSuccess = MutableLiveData<Boolean>()

    /**
     * 最新项目
     */
    var newProjectList = MutableLiveData<ProjectBean>()

    /**
     * 最新项目网络异常
     */
    var newProjectListFail = MutableLiveData<ApiException>()

    /**
     * 项目列表
     */
    var projectList = MutableLiveData<ProjectBean>()

    /**
     * 项目列表网络异常
     */
    var projectListFail = MutableLiveData<ApiException>()

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
     * 获取最新项目
     * @param pageNum 分页
     */
    fun getNewProjectList(pageNum: Int) {
        launch({
            newProjectList.value = repository.getNewProjectList(pageNum)
        }, {
            newProjectListFail.value = it
        })
    }

    /**
     * 项目分类详情列表
     * @param pageNum 分页
     * @param cid 项目分类id
     */
    fun getProjectList(pageNum: Int, cid: Int) {
        launch({
            projectList.value = repository.getProjectList(pageNum, cid)
        }, {
            projectListFail.value = it
        })
    }
}