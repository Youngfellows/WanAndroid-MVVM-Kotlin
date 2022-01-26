package com.shehuan.nicedialog;

import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;


/**
 * 绑定视图得ViewHolder
 */
public class ViewHolder {

    /**
     * 缓存该View的子View
     */
    private SparseArray<View> views;

    /**
     * 需要绑定ViewHolder的视图
     */
    private View convertView;

    private ViewHolder(View view) {
        convertView = view;
        views = new SparseArray<>();
    }

    /***
     * 为每一个View创建一个ViewHolder
     * @param view 视图
     * @return
     */
    public static ViewHolder create(View view) {
        return new ViewHolder(view);
    }

    /**
     * 根据ID获取子View并保存该子View到map
     *
     * @param viewId 子View的Id
     * @param <T>    子View
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 获取该内容的View
     *
     * @return
     */
    public View getConvertView() {
        return convertView;
    }

    /**
     * 设置指定TextView的文本
     *
     * @param viewId 设置指定TextView的ID
     * @param text   设置内容
     */
    public void setText(int viewId, String text) {
        TextView textView = getView(viewId);
        textView.setText(text);
    }

    /**
     * 设置指定TextView的Id
     *
     * @param viewId 设置指定TextView的ID
     * @param textId 设置内容
     */
    public void setText(int viewId, int textId) {
        TextView textView = getView(viewId);
        textView.setText(textId);
    }

    /**
     * 设置自定TextView的字体color
     *
     * @param viewId  设置指定TextView的ID
     * @param colorId 设置的textView的字体颜色
     */
    public void setTextColor(int viewId, int colorId) {
        TextView textView = getView(viewId);
        textView.setTextColor(colorId);
    }

    /**
     * 设置指定控件的点击事件
     *
     * @param viewId        指定控件的ID
     * @param clickListener 点击事件接口
     */
    public void setOnClickListener(int viewId, View.OnClickListener clickListener) {
        View view = getView(viewId);
        view.setOnClickListener(clickListener);
    }

    /**
     * 设置指定View的背景资源
     *
     * @param viewId 指定控件的ID
     * @param resId  指定View的背景资源
     */
    public void setBackgroundResource(int viewId, int resId) {
        View view = getView(viewId);
        view.setBackgroundResource(resId);
    }

    /**
     * 设置指定View的背景颜色资源
     *
     * @param viewId  指定控件的ID
     * @param colorId 指定View的背景颜色资源
     */
    public void setBackgroundColor(int viewId, int colorId) {
        View view = getView(viewId);
        view.setBackgroundColor(colorId);
    }
}
