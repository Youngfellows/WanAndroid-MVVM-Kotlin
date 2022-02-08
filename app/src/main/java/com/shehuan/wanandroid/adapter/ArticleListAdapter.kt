package com.shehuan.wanandroid.adapter

import android.content.Context
import com.othershe.baseadapter.ViewHolder
import com.othershe.baseadapter.base.CommonBaseAdapter
import com.shehuan.wanandroid.R
import com.shehuan.wanandroid.bean.article.DatasItem
import com.shehuan.wanandroid.databinding.RvItemArticleLayoutBinding
import com.shehuan.wanandroid.base.initDataBinding

/**
 * 文章列表适配器
 * @constructor
 *
 * @param context 上下文
 * @param data 数据集
 * @param isOpenLoadMore 是否打开加载更多
 */
class ArticleListAdapter(context: Context?, data: List<DatasItem>?, isOpenLoadMore: Boolean) :
    CommonBaseAdapter<DatasItem>(context, data, isOpenLoadMore) {

    /**
     * @return 加载文章列表Item
     */
    override fun getItemLayoutId(): Int {
        return R.layout.rv_item_article_layout
    }

    /**
     * 为Item绑定数据
     * @param viewHolder 视图
     * @param data item数据
     * @param position 位置
     */
    override fun convert(viewHolder: ViewHolder, data: DatasItem, position: Int) {
        val binding =
            initDataBinding<RvItemArticleLayoutBinding>(
                viewHolder.convertView
            )
        binding.data = data
    }
}