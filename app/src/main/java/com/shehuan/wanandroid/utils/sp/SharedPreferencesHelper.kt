package com.shehuan.wanandroid.utils.sp

import android.content.Context
import android.content.SharedPreferences
import com.shehuan.wanandroid.App
import kotlin.IllegalArgumentException

/**
 *SharedPreferences工具类,伴生对象静态方法
 */
object SharedPreferencesHelper {

    /**
     * 获取SharedPreferences
     */
    private val sharedPreferences: SharedPreferences by lazy {
        App.getApp().getSharedPreferences("ksp", Context.MODE_PRIVATE)
    }

    /**
     * 向SP中插入数据
     * @param key 键
     * @param value 值
     */
    fun put(key: String, value: Any) = with(sharedPreferences.edit()) {
        when (value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Float -> putFloat(key, value)
            is Long -> putLong(key, value)
            is Boolean -> putBoolean(key, value)
            else -> throw IllegalArgumentException("This type of value cannot be save!")
        }.apply()
    }

    /**
     * 根据Key和默认类型或者SP中保存的值
     * @param T  返回的泛型
     * @param key 键
     * @param defaultValue 默认返回的泛型
     * @return
     */
    @Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")
    fun <T> get(key: String, defaultValue: T): T = with(sharedPreferences) {
        val value = when (defaultValue) {
            is String -> getString(key, defaultValue)
            is Int -> getInt(key, defaultValue)
            is Float -> getFloat(key, defaultValue)
            is Long -> getLong(key, defaultValue)
            is Boolean -> getBoolean(key, defaultValue)
            else -> throw IllegalArgumentException("This type of value cannot be get!")
        }

        return value as T
    }

    /**
     * SP中是否包含某个Key
     * @param key
     */
    fun contain(key: String) {
        sharedPreferences.contains(key)
    }

    /**
     * 移除SP中指定key的值
     * @param key
     */
    fun remove(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    /**
     *清空SP
     */
    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}