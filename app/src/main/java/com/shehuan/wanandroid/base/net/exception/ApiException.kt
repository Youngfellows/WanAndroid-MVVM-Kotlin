package com.shehuan.wanandroid.base.net.exception

/**
 * 网络请求异常
 *
 * @property errorCode
 * @property errorMessage
 */
class ApiException(val errorCode: Int, val errorMessage: String) : RuntimeException(errorMessage) {

}