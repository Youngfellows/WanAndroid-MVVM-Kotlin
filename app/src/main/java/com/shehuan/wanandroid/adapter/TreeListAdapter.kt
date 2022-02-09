package com.shehuan.wanandroid.adapter

import android.content.Context
import com.othershe.baseadapter.ViewHolder
import com.othershe.baseadapter.base.CommonBaseAdapter
import com.shehuan.wanandroid.R
import com.shehuan.wanandroid.bean.tree.TreeBean
import com.shehuan.wanandroid.databinding.RvItemTreeLayoutBinding
import com.shehuan.wanandroid.base.initDataBinding

/**
 *  体系列表适配器
 * @constructor
 *
 * @param context 上下文
 * @param data 数据集
 * @param isOpenLoadMore 是否加载更多
 */
class TreeListAdapter(context: Context?, data: List<TreeBean>?, isOpenLoadMore: Boolean) :
    CommonBaseAdapter<TreeBean>(context, data, isOpenLoadMore) {

    /**
     * 加载Item
     * @return
     */
    override fun getItemLayoutId(): Int {
        return R.layout.rv_item_tree_layout
    }

    /**
     * 绑定数据
     * @param viewHolder 视图
     * @param data 数据
     * @param position 位置
     */
    override fun convert(viewHolder: ViewHolder, data: TreeBean, position: Int) {
        val binding =
            initDataBinding<RvItemTreeLayoutBinding>(
                viewHolder.convertView
            )
        binding.data = data
    }
}