package com.shehuan.wanandroid.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

/**
 * 显示网络图片工具
 */
class ImageLoader {

    companion object {
        /**
         * 显示网络图片
         * @param context
         * @param url 网络图片资源
         * @param iv 图片
         */
        fun load(context: Context, url: String, iv: ImageView) {
            Glide.with(context)
                .load(url)
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .transition(withCrossFade())
                .into(iv)
        }
    }
}