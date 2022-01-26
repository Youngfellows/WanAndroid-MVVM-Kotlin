package com.shehuan.wanandroid.widget

import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.shehuan.nicedialog.BaseNiceDialog
import com.shehuan.nicedialog.ViewHolder
import com.shehuan.wanandroid.R

class LogoutDialog : BaseNiceDialog() {

    /**
     * 为对话框绑定视图及点击事件
     * @param viewHolder 绑定视图的ViewHolder
     * @param dialog  当前对话框
     */
    override fun convertView(viewHolder: ViewHolder, dialog: BaseNiceDialog) {
        setMargin(60)
        with(viewHolder) {
            //取消对话框
            getView<TextView>(R.id.logoutCancelBtn).setOnClickListener {
                dialog.dismiss()
            }
            //登出
            getView<TextView>(R.id.logoutOkBtn).setOnClickListener {
                listener.logout()
                dialog.dismiss()
            }
        }
    }

    /**
     * 加载布局资源
     * @return
     */
    override fun intLayoutId(): Int {
        return R.layout.dialog_logout_layout
    }

    /**
     * 单例,静态方法
     */
    companion object {
        fun show(fragmentManager: FragmentManager, listener: OnLogoutListener) {
            LogoutDialog().setOnLogoutListener(listener).show(fragmentManager)
        }
    }

    /**
     * 延迟加载,退出接口
     */
    private lateinit var listener: OnLogoutListener

    /**
     * 设置登出回调
     * @param listener
     * @return
     */
    fun setOnLogoutListener(listener: OnLogoutListener): LogoutDialog {
        this.listener = listener
        return this
    }

    /**
     * 退出接口
     */
    interface OnLogoutListener {
        /**
         * 退出
         */
        fun logout()
    }
}