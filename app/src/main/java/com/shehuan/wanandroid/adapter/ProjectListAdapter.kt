package com.shehuan.wanandroid.adapter

import android.content.Context
import com.othershe.baseadapter.ViewHolder
import com.othershe.baseadapter.base.CommonBaseAdapter
import com.shehuan.wanandroid.R
import com.shehuan.wanandroid.bean.project.DatasItem
import com.shehuan.wanandroid.databinding.RvItemProjectLayoutBinding
import com.shehuan.wanandroid.base.initDataBinding

/**
 * 项目列表适配器
 * @constructor
 *
 * @param context
 * @param data 数据集
 * @param isOpenLoadMore
 */
class ProjectListAdapter(context: Context?, data: List<DatasItem>?, isOpenLoadMore: Boolean) :
    CommonBaseAdapter<DatasItem>(context, data, isOpenLoadMore) {

    /**
     * 加载Item布局
     * @return
     */
    override fun getItemLayoutId(): Int {
        return R.layout.rv_item_project_layout
    }

    /**
     * 绑定数据
     * @param viewHolder
     * @param data
     * @param position
     */
    override fun convert(viewHolder: ViewHolder, data: DatasItem, position: Int) {
        val binding =
            initDataBinding<RvItemProjectLayoutBinding>(
                viewHolder.convertView
            )
        binding.data = data
    }
}