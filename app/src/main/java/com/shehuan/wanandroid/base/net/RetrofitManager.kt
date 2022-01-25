package com.shehuan.wanandroid.base.net

import com.shehuan.wanandroid.apis.WanAndroidApis
import com.shehuan.wanandroid.base.Const
import com.shehuan.wanandroid.base.net.interceptor.AddCookiesInterceptor
import com.shehuan.wanandroid.base.net.interceptor.HttpLogger
import com.shehuan.wanandroid.base.net.interceptor.SaveCookiesInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 网络请求工具,单例的
 */
object RetrofitManager {

    /**
     * 延迟获取OkHttpClient
     */
    private val okHttpClient: OkHttpClient by lazy {
        getOkHttpClient(true)
    }

    /**
     * 延迟获取Retrofit
     */
    private val retrofit: Retrofit by lazy {
        initRetrofit()
    }

    private lateinit var apis: WanAndroidApis

    fun getApis(): WanAndroidApis {
        if (!this::apis.isInitialized) {
            apis = create(WanAndroidApis::class.java)
        }
        return apis
    }

    /**
     * 创建 请求接口 WanAndroidApis的代理
     * @param S  WanAndroidApis的代理
     * @param service WanAndroidApis的代理Class
     * @return WanAndroidApis的代理
     */
    fun <S> create(service: Class<S>): S = retrofit.create(service)

    /**
     *初始化Retrofit,并返回
     */
    private fun initRetrofit() = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Const.WAN_ANDROID_URL)
        .build()

    /**
     * 初始化OkHttpClient并配置请求cookies等
     * @param flag
     * @return OkHttpClient
     */
    private fun getOkHttpClient(flag: Boolean): OkHttpClient {
        //配置超时拦截器
        val builder = OkHttpClient.Builder().apply {
            connectTimeout(10, TimeUnit.SECONDS)
            writeTimeout(10, TimeUnit.SECONDS)
            readTimeout(10, TimeUnit.SECONDS)

            if (flag) {
                //配置log打印拦截器
                val loggingInterceptor = HttpLoggingInterceptor(HttpLogger())
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                //addInterceptor(loggingInterceptor)
                addNetworkInterceptor(loggingInterceptor)
            }

            // 请求相应拦截器
            addInterceptor(SaveCookiesInterceptor())
            addInterceptor(AddCookiesInterceptor())
        }

        return builder.build()
    }
}