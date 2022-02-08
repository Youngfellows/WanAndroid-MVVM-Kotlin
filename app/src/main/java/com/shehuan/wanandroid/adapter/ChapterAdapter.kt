package com.shehuan.wanandroid.adapter

import android.content.Context
import com.othershe.baseadapter.ViewHolder
import com.othershe.baseadapter.base.CommonBaseAdapter
import com.shehuan.wanandroid.R
import com.shehuan.wanandroid.bean.ChapterBean
import com.shehuan.wanandroid.databinding.RvItemChapterLayoutBinding
import com.shehuan.wanandroid.base.initDataBinding

/**
 * 公众号适配器
 * @constructor
 * TODO
 *
 * @param context 上下文
 * @param data 数据集
 * @param isOpenLoadMore 是否加载更多
 */
class ChapterAdapter(context: Context?, data: List<ChapterBean>?, isOpenLoadMore: Boolean) :
    CommonBaseAdapter<ChapterBean>(context, data, isOpenLoadMore) {

    private val colors = intArrayOf(
        R.color.c9DD3FA,
        R.color.cF7F7D0,
        R.color.cFFC09F,
        R.color.cA0D8DE,
        R.color.cE2DBBE,
        R.color.cEAE1F0
    )

    /**
     * 加载Item布局
     * @return
     */
    override fun getItemLayoutId(): Int {
        return R.layout.rv_item_chapter_layout
    }

    /**
     * 绑定数据集
     * @param viewHolder 视图
     * @param data 数据
     * @param position 位置
     */
    override fun convert(viewHolder: ViewHolder, data: ChapterBean, position: Int) {
        val binding =
            initDataBinding<RvItemChapterLayoutBinding>(
                viewHolder.convertView
            )
        binding.data = data
        binding.color = mContext.resources.getColor(colors[position % 6])
    }
}