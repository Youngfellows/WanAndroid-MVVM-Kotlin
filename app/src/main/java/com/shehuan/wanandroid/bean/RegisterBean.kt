package com.shehuan.wanandroid.bean

/**
 * 注册账号的响应实体
 * @property password
 * @property icon
 * @property id
 * @property type
 * @property email
 * @property token
 * @property username
 */
data class RegisterBean(
    val password: String = "",
    val icon: String = "",
    val id: Int = 0,
    val type: Int = 0,
    val email: String = "",
    val token: String = "",
    val username: String = ""
)