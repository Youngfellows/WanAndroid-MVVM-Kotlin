package com.shehuan.wanandroid.ui.main

import android.content.Context
import android.content.Intent
import android.os.Handler
import androidx.core.view.GravityCompat
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.shehuan.wanandroid.R
import com.shehuan.wanandroid.adapter.ViewPagerAdapter
import com.shehuan.wanandroid.base.activity.BaseActivity
import com.shehuan.wanandroid.bean.event.AccountEvent
import com.shehuan.wanandroid.ui.about.AboutActivity
import com.shehuan.wanandroid.ui.home.HomeFragment
import com.shehuan.wanandroid.ui.nav.NavFragment
import com.shehuan.wanandroid.ui.chapter.ChapterFragment
import com.shehuan.wanandroid.ui.collection.MyCollectionActivity
import com.shehuan.wanandroid.ui.login.LoginActivity
import com.shehuan.wanandroid.ui.project.ProjectFragment
import com.shehuan.wanandroid.ui.query.QueryActivity
import com.shehuan.wanandroid.ui.tree.TreeFragment
import com.shehuan.wanandroid.utils.ToastUtil
import com.shehuan.wanandroid.base.initViewModel
import com.shehuan.wanandroid.utils.sp.SpUtil
import com.shehuan.wanandroid.widget.BottomTabLayout
import com.shehuan.wanandroid.widget.LogoutDialog
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : BaseActivity() {

    /**
     * 初始化MainViewModel,MainRepository是MainViewModel的参数,并关联起来
     */
    private val viewModel by lazy {
        initViewModel(this, MainViewModel::class, MainRepository::class)
    }

    /**
     * 是否按下返回按键
     */
    private var isBackPressed: Boolean = false

    /**
     * 延迟加载初始化
     */
    private lateinit var usernameTv: TextView

    /**
     * 静态属性和静态方法
     */
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    /**
     * 初始化加载项
     */
    override fun initLoad() {

    }

    /**
     * 加载布局
     */
    override fun initContentView() {
        setContentView(R.layout.activity_main)
    }

    /**
     * 初始化数据集
     */
    override fun initData() {
        EventBus.getDefault().register(this)
        /*viewModel.logoutSuccess.observe(this, Observer { success ->
            hideLoading()
            if (success) {
                usernameTv.text = getString(R.string.login)
            }
        })*/

        //使用对象表达式,观察数据变化
        viewModel.logoutSuccess.observe(this, object : Observer<Boolean> {
            override fun onChanged(success: Boolean?) {
                hideLoading()
                if (success!!) {
                    usernameTv.text = getString(R.string.login)
                }
            }
        })
    }

    /**
     * 初始化视图和绑定轮播页
     */
    override fun initView() {
        //点击关闭抽屉
        mainMenuIv.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        //打开搜索页
        mainQueryIv.setOnClickListener {
            QueryActivity.start(this)
        }

        //加载侧滑抽屉头
        usernameTv = navigationView.inflateHeaderView(R.layout.navigation_view_header_layout)
            .findViewById(R.id.usernameTv)
        usernameTv.text = SpUtil.getUsername()
        usernameTv.setOnClickListener {
            if (getString(R.string.login) == usernameTv.text) {
                //跳转登录页
                LoginActivity.start(this)
            }
        }
        //加载侧滑抽屉菜单项
        navigationView.inflateMenu(R.menu.navigation_view_menu_layout)
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_item_collect -> collection()
                R.id.nav_item_setting -> setting()
                R.id.nav_item_about -> about()
                R.id.nav_item_logout -> logout()
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }

        // 初始化ViewPager
        val fragments = arrayListOf<Fragment>().apply {
            add(HomeFragment.newInstance())
            add(ProjectFragment.newInstance())
            add(TreeFragment.newInstance())
            add(NavFragment.newInstance())
            add(ChapterFragment.newInstance())
        }
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.setFragments(fragments)
        mainViewpager.adapter = viewPagerAdapter
        mainViewpager.offscreenPageLimit = fragments.size

        // 初始化底部Tab
        mainBottomTabLayout.run {
            setupWithViewPager(mainViewpager)
            setOnTabSwitchListener(object : BottomTabLayout.OnTabSwitchListener {
                override fun onTabSwitch(tabIndex: Int, tabName: String) {
                    tabNameTv.text = tabName
                }
            })
            addTab(getString(R.string.home), R.drawable.ic_homepage, R.drawable.ic_homepage_fill)
            addTab(
                getString(R.string.project),
                R.drawable.ic_createtask,
                R.drawable.ic_createtask_fill
            )
            addTab(getString(R.string.tree), R.drawable.ic_manage, R.drawable.ic_manage_fill)
            addTab(
                getString(R.string.nav),
                R.drawable.ic_coordinates,
                R.drawable.ic_coordinates_fill
            )
            addTab(getString(R.string.chapter), R.drawable.ic_select, R.drawable.ic_select_fill)
        }
    }

    /**
     * 跳转到收藏页
     */
    private fun collection() {
        if (SpUtil.getCookies().isEmpty()) {
            ToastUtil.show(mContext, R.string.login_tip)
            return
        }
        MyCollectionActivity.start(mContext)
    }

    /**
     * 跳转到设置页
     */
    private fun setting() {
        ToastUtil.show(mContext, R.string.setting_tip)
    }

    /**
     * 跳转到关于页面
     */
    private fun about() {
        AboutActivity.start(this)
    }

    /**
     * 登出
     */
    private fun logout() {
        if (SpUtil.getCookies().isEmpty()) {
            ToastUtil.show(mContext, R.string.login_tip)
            return
        }
        //对象表达式,登出
        LogoutDialog.show(supportFragmentManager, object : LogoutDialog.OnLogoutListener {
            override fun logout() {
                showLoading()
                viewModel.logout()
            }
        })

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAccountEvent(event: AccountEvent) {
        usernameTv.text = SpUtil.getUsername()
    }

    /**
     * 监听返回按键及双击退出
     */
    override fun onBackPressed() {
        //关闭抽屉
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            //再按一次退出
            if (isBackPressed) {
                super.onBackPressed()
                return
            }
            isBackPressed = true
            ToastUtil.show(mContext, R.string.exit_tip)
            Handler().postDelayed({ isBackPressed = false }, 2000)
        }
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }
}
