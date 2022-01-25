package com.shehuan.wanandroid.utils.sp

/**
 *SharedPreferences工具类,伴生对象静态方法
 */
class SpUtil {

    companion object {

        /**
         * 获取保存到SP的cookies
         * @return cookies
         */
        fun getCookies(): String {
            return SharedPreferencesHelper.get("cookies", "")
        }

        /**
         * 保存Cookies
         * @param version cookies
         */
        fun setCookies(version: String) {
            SharedPreferencesHelper.put("cookies", version)
        }

        /**
         * 移除cookies
         */
        fun removeCookies() {
            SharedPreferencesHelper.remove("cookies")
        }

        /**
         * 获取用户名
         * @return 用户名
         */
        fun getUsername(): String {
            return SharedPreferencesHelper.get("username", "登录")
        }

        /**
         * 保存用户名
         * @param username 用户名
         */
        fun setUsername(username: String) {
            SharedPreferencesHelper.put("username", username)
        }

        /**
         * 移除用户名
         */
        fun removeUsername() {
            SharedPreferencesHelper.remove("username")
        }
    }
}