package com.shehuan.wanandroid.base.net.interceptor

import com.shehuan.wanandroid.utils.sp.SpUtil
import okhttp3.Interceptor
import okhttp3.Response


/**
 * 网络请求时添加cookie
 */
class AddCookiesInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = chain.request().newBuilder()
//        if (request.url().toString().contains("collect")) {
        val cookies = SpUtil.getCookies().split("#")
        for (cookie in cookies) {
            builder.addHeader("Cookie", cookie)
        }
//        }
        return chain.proceed(builder.build())
    }
}