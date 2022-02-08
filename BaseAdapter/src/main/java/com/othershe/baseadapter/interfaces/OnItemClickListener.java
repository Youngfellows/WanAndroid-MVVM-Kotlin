package com.othershe.baseadapter.interfaces;

import com.othershe.baseadapter.ViewHolder;

/**
 * 条目点击回调
 *
 * @param <T> 数据
 */
public interface OnItemClickListener<T> {

    /**
     * @param viewHolder 点击条目
     * @param data       数据
     * @param position   位置
     */
    void onItemClick(ViewHolder viewHolder, T data, int position);
}
