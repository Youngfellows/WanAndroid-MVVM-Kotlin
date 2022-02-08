package com.shehuan.wanandroid.ui.chapter

import androidx.lifecycle.MutableLiveData
import com.shehuan.wanandroid.base.BaseViewModel
import com.shehuan.wanandroid.base.net.exception.ApiException
import com.shehuan.wanandroid.bean.ChapterBean

/**
 * 公众号的ViewModel
 * @property repository 具体执行任务的repository
 */
class ChapterViewModel(private val repository: ChapterRepository) : BaseViewModel() {

    /**
     * 公众号列表
     */
    var chapterList = MutableLiveData<List<ChapterBean>>()

    /**
     * 网络异常
     */
    var chapterListFail = MutableLiveData<ApiException>()

    /**
     * 获取公众号列表
     */
    fun getChapterList() {
        launch({
            chapterList.value = repository.getChapterList()
        }, {
            chapterListFail.value = it
        })
    }
}