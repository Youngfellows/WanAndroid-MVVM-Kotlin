package com.othershe.baseadapter.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.interfaces.OnItemChildClickListener;
import com.othershe.baseadapter.interfaces.OnItemClickListener;
import com.othershe.baseadapter.interfaces.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用的CommonBaseAdapter
 *
 * @param <T> 泛型数据
 */
public abstract class CommonBaseAdapter<T> extends BaseAdapter<T> {

    /**
     * 条目点击回调
     */
    private OnItemClickListener<T> mItemClickListener;

    /**
     * 条目长按事件监听
     */
    private OnItemLongClickListener<T> mItemLongClickListener;

    /**
     * 条目子控件ID列表
     */
    private ArrayList<Integer> mItemChildIds = new ArrayList<>();

    /**
     * 条目子控件点击监听
     */
    private ArrayList<OnItemChildClickListener<T>> mItemChildListeners = new ArrayList<>();

    public CommonBaseAdapter(Context context, List<T> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    /**
     * 抽象方法,将视图交给子类去绑定
     *
     * @param holder   视图
     * @param data     数据
     * @param position 位置
     */
    protected abstract void convert(ViewHolder holder, T data, int position);

    /**
     * 加载Item布局
     *
     * @return 布局layout资源
     */
    protected abstract int getItemLayoutId();

    /**
     * @param parent
     * @param viewType 视图类型
     * @return 创建视图
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (isCommonItemView(viewType)) {
            return ViewHolder.create(mContext, getItemLayoutId(), parent);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    /**
     * 为视图绑定数据
     *
     * @param holder   视图
     * @param position 位置
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        if (isCommonItemView(viewType)) {
            bindCommonItem(holder, position - getHeaderCount());
        }
    }

    /**
     * 为普通视图绑定数据
     *
     * @param holder   视图
     * @param position 位置
     */
    private void bindCommonItem(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        convert(viewHolder, getAllData().get(position), position);

        //设置Item点击事件
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(viewHolder, getAllData().get(position), position);
                }
            }
        });

        //设置Item长按事件
        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mItemLongClickListener != null) {
                    mItemLongClickListener.onItemLongClick(viewHolder, getAllData().get(position), position);
                }
                return true;
            }
        });

        //设置Item身上的子控件的点击事件
        for (int i = 0; i < mItemChildIds.size(); i++) {
            final int tempI = i;
            if (viewHolder.getConvertView().findViewById(mItemChildIds.get(i)) != null) {
                viewHolder.getConvertView().findViewById(mItemChildIds.get(i)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mItemChildListeners.get(tempI).onItemChildClick(viewHolder, getAllData().get(position), position);
                    }
                });
            }
        }
    }

    /**
     * @param position 条目位置
     * @param data     数据
     * @return 普通条目类型
     */
    @Override
    protected int getViewType(int position, T data) {
        return TYPE_COMMON_VIEW;
    }

    public void setOnItemClickListener(OnItemClickListener<T> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<T> itemLongClickListener) {
        mItemLongClickListener = itemLongClickListener;
    }

    public void setOnItemChildClickListener(int viewId, OnItemChildClickListener<T> itemChildClickListener) {
        mItemChildIds.add(viewId);
        mItemChildListeners.add(itemChildClickListener);
    }

}
