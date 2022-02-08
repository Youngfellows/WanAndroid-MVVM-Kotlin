package com.othershe.baseadapter.interfaces;

import com.othershe.baseadapter.ViewHolder;

/**
 * 条目长按事件监听
 *
 * @param <T> 泛型数据
 */
public interface OnItemLongClickListener<T> {
    /**
     * @param viewHolder 点击条目
     * @param data       数据
     * @param position   位置
     */
    void onItemLongClick(ViewHolder viewHolder, T data, int position);
}
