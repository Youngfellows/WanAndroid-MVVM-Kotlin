package com.shehuan.wanandroid.base.activity

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.shehuan.statusview.StatusView
import com.shehuan.statusview.StatusViewBuilder
import com.shehuan.wanandroid.R
import com.shehuan.wanandroid.widget.LoadingDialog
import kotlinx.android.synthetic.main.toolbar_layout.*

abstract class BaseActivity : AppCompatActivity() {

    protected val TAG: String = this.javaClass.simpleName

    /**
     * 上下文:延迟加载初始化
     */
    lateinit var mContext: BaseActivity

    /**
     *初始化加载资源布局
     */
    abstract fun initContentView()

    /**
     *初始化数据
     */
    abstract fun initData()

    /**
     *初始化视图
     */
    abstract fun initView()

    /**
     *初始化加载项
     */
    abstract fun initLoad()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContentView()
        mContext = this

        // 实现APP黑白化
//        val paint = Paint()
//        val cm = ColorMatrix();
//        cm.setSaturation(0f)
//        paint.colorFilter = ColorMatrixColorFilter(cm)
//        window.decorView.setLayerType(View.LAYER_TYPE_HARDWARE, paint)

        initData()
        initView()
        initLoad()
    }

    /**
     * 状态视图
     */
    protected lateinit var statusView: StatusView

    /**
     * 初始化状态视图
     *
     * @param id 状态布局资源
     * @param errorRetry
     */
    protected fun initStatusView(id: Int, errorRetry: (View) -> Unit) {
        statusView = StatusView.init(this, id).apply {
            setLoadingView(R.layout.dialog_loading_layout)
            config(
                StatusViewBuilder.Builder()
                    .showEmptyRetry(false)
                    .setOnErrorRetryClickListener(errorRetry)
                    .build()
            )
        }
    }

    /**
     * 初始化头部
     *
     * @param titleId 头部标题
     */
    protected fun initToolbar(@StringRes titleId: Int) {
        initToolbar(getString(titleId))
    }

    /**
     * TODO初始化头部
     *
     * @param titleStr 头部标题
     */
    protected fun initToolbar(titleStr: String) {
        toolbar.run {
            title = titleStr
            setSupportActionBar(this)
            setNavigationOnClickListener {
                finish()
            }
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    /**
     * 加载对话框
     */
    private var loadingDialog: LoadingDialog? = null

    /**
     * 显示加载对话框
     */
    protected fun showLoading() {
        if (loadingDialog == null || loadingDialog?.dialog == null) {
            loadingDialog = LoadingDialog.newInstance()
            loadingDialog!!.show(supportFragmentManager)
        }
    }

    /**
     *隐藏加载对话框
     */
    protected fun hideLoading() {
        if (loadingDialog != null) {
            loadingDialog!!.dismiss()
        }
    }
}