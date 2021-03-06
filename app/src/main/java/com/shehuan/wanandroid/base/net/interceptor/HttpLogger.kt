package com.shehuan.wanandroid.base.net.interceptor

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor

/**
 *网络拦截器
 */
class HttpLogger : HttpLoggingInterceptor.Logger {

    private val TAG: String = this.javaClass.simpleName

    override fun log(message: String) {
        Log.w(TAG, message)
    }
}