package com.shehuan.wanandroid.adapter

import android.content.Context
import com.othershe.baseadapter.ViewHolder
import com.othershe.baseadapter.base.CommonBaseAdapter
import com.shehuan.wanandroid.R
import com.shehuan.wanandroid.bean.treeDetail.DatasItem
import com.shehuan.wanandroid.databinding.RvItemTreeDetailLayoutBinding
import com.shehuan.wanandroid.base.initDataBinding

/**
 * 体系详情适配器
 * @constructor
 * TODO
 *
 * @param context 上下文
 * @param data 数据集
 * @param isOpenLoadMore 是否加载更多
 */
class TreeDetailListAdapter(context: Context?, data: List<DatasItem>?, isOpenLoadMore: Boolean) :
    CommonBaseAdapter<DatasItem>(context, data, isOpenLoadMore) {

    /**
     * 加载资源
     * @return
     */
    override fun getItemLayoutId(): Int {
        return R.layout.rv_item_tree_detail_layout
    }

    /**
     * 绑定数据
     * @param viewHolder 视图
     * @param data 数据
     * @param position 位置
     */
    override fun convert(viewHolder: ViewHolder, data: DatasItem, position: Int) {
        val binding =
            initDataBinding<RvItemTreeDetailLayoutBinding>(
                viewHolder.convertView
            )
        binding.data = data
    }
}