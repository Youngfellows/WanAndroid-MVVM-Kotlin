package com.shehuan.wanandroid.base.net

/**
 * 网络响应
 * @param T  响应的实体泛型
 * @property errorMsg 错误消息
 * @property errorCode 错误码
 * @property data 响应的实体泛型
 */
data class BaseResponse<T>(val errorMsg: String, val errorCode: Int, var data: T) {

}