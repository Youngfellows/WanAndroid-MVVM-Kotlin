package com.shehuan.wanandroid.bean

/**
 * 轮播图数据类
 * @property desc
 * @property id
 * @property imagePath
 * @property isVisible
 * @property order
 * @property title
 * @property type
 * @property url
 */
data class BannerBean(
    val desc: String, val id: Int, val imagePath: String,
    val isVisible: Int, val order: Int, val title: String,
    val type: Int, val url: String
)