package com.shehuan.wanandroid.bean

/**
 * 登录响应实体类
 * @property password
 * @property icon
 * @property collectIds
 * @property id
 * @property type
 * @property email
 * @property token
 * @property username
 */
data class LoginBean(
    val password: String = "",
    val icon: String = "",
    val collectIds: List<Int>?,
    val id: Int = 0,
    val type: Int = 0,
    val email: String = "",
    val token: String = "",
    val username: String = ""
)