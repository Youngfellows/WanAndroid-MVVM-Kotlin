package com.shehuan.wanandroid.adapter

import android.content.Context
import com.othershe.baseadapter.ViewHolder
import com.othershe.baseadapter.base.CommonBaseAdapter
import com.shehuan.wanandroid.R
import com.shehuan.wanandroid.bean.article.DatasItem
import com.shehuan.wanandroid.databinding.RvItemArticleLayoutBinding
import com.shehuan.wanandroid.base.initDataBinding

/**
 * 查询结果适配器
 * @constructor
 * TODO
 *
 * @param context
 * @param data
 * @param isOpenLoadMore
 */
class QueryResultAdapter(context: Context?, data: List<DatasItem>?, isOpenLoadMore: Boolean) :
    CommonBaseAdapter<DatasItem>(context, data, isOpenLoadMore) {

    /**
     * 加载布局
     * @return
     */
    override fun getItemLayoutId(): Int {
        return R.layout.rv_item_article_layout
    }

    /**
     * 绑定数据
     * @param viewHolder
     * @param data
     * @param position
     */
    override fun convert(viewHolder: ViewHolder, data: DatasItem, position: Int) {
        val binding =
            initDataBinding<RvItemArticleLayoutBinding>(
                viewHolder.convertView
            )
        binding.data = data
    }
}