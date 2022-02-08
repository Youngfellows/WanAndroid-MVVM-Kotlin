package com.shehuan.wanandroid.ui.home

import android.util.Log
import com.shehuan.wanandroid.R
import com.shehuan.wanandroid.adapter.ArticleListAdapter
import com.shehuan.wanandroid.bean.BannerBean
import com.shehuan.wanandroid.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shehuan.wanandroid.base.fragment.BaseFragment
import com.shehuan.wanandroid.bean.article.DatasItem
import com.shehuan.wanandroid.ui.article.ArticleActivity
import com.shehuan.wanandroid.utils.ToastUtil
import com.youth.banner.Banner
import com.shehuan.wanandroid.utils.BannerImageLoader
import com.shehuan.wanandroid.base.initViewModel
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.floating_button_layout.*
import java.lang.IllegalArgumentException

/**
 * 首页
 */
class HomeFragment : BaseFragment() {

    /**
     * 初始化ViewModel
     */
    private val viewModel by lazy {
        initViewModel(
            this, HomeViewModel::class, HomeRepository::class
        )
    }

    /**
     * 第几页
     */
    private var pageNum: Int = 0

    /**
     * 文章列表适配器
     */
    private lateinit var articleListAdapter: ArticleListAdapter

    /**
     * 收藏的列表项目
     */
    private lateinit var collectDataItem: DatasItem

    /**
     * 收藏的列表项目索引
     */
    private var collectPosition: Int = 0

    /**
     * 轮播图数据集
     */
    private lateinit var bannerBeans: List<BannerBean>

    /**
     * 轮播图
     */
    private lateinit var banner: Banner

    /**
     * 静态方法,属性
     */
    companion object {
        fun newInstance() = HomeFragment()
    }

    /**
     * 加载数据
     */
    override fun initLoad() {
        Log.w(TAG, Log.getStackTraceString(IllegalArgumentException("initLoad()")))
        statusView.showLoadingView()
        viewModel.getArticleList(pageNum)
        viewModel.getBannerList()
    }

    /**
     * 加载首页布局
     * @return
     */
    override fun initLayoutResID(): Int {
        return R.layout.fragment_home
    }

    /**
     * 初始化数据监听
     */
    override fun initData() {
        viewModel.collectSuccess.observe(this, Observer { success ->
            hideLoading()
            if (success) {
                collectDataItem.collect = true
                articleListAdapter.change(collectPosition + 1)
                ToastUtil.show(mContext, R.string.collect_success)
            }
        })

        viewModel.uncollectSuccess.observe(this, Observer { success ->
            hideLoading()
            if (success) {
                collectDataItem.collect = false
                articleListAdapter.change(collectPosition + 1)
                ToastUtil.show(mContext, R.string.uncollect_success)
            }
        })

        viewModel.bannerList.observe(this, Observer {
            statusView.showContentView()
            bannerBeans = it
            val images = arrayListOf<String>()
            val titles = arrayListOf<String>()

            for (bannerBean in it) {
                images.add(bannerBean.imagePath)
                titles.add(bannerBean.title)
            }
            banner.run {
                setImages(images)
                setBannerTitles(titles)
                start()
            }
        })

        viewModel.articleList.observe(this, Observer {
            if (pageNum == 0) {
                articleListAdapter.setNewData(it.datas)
            } else {
                articleListAdapter.setLoadMoreData(it.datas)
            }
            pageNum++
            if (pageNum == it.pageCount) {
                articleListAdapter.loadEnd()
            }
        })

        viewModel.articleListFail.observe(this, Observer {
            articleListAdapter.loadFailed()
        })
    }

    /**
     * 初始化视图
     */
    override fun initView() {
        // 初始化banner
        banner = LayoutInflater.from(context).inflate(
            R.layout.home_banner_layout,
            homeRootLayout,
            false
        ) as Banner
        banner.run {
            setImageLoader(BannerImageLoader())
            setDelayTime(3000)
            setIndicatorGravity(BannerConfig.RIGHT)
            setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
            setOnBannerListener {
                ArticleActivity.start(mContext, bannerBeans[it].title, bannerBeans[it].url)
            }
        }

        // 悬浮按钮点击事件
        floatBtn.hide()
        floatBtn.setOnClickListener {
            articleRv.smoothScrollToPosition(0)
        }

        //文章列表适配器
        articleListAdapter = ArticleListAdapter(context, null, true).apply {
            setLoadingView(R.layout.rv_loading_layout)
            setLoadEndView(R.layout.rv_load_end_layout)
            setLoadFailedView(R.layout.rv_load_failed_layout)
            // 添加banner
            addHeaderView(banner)

            setOnItemClickListener { _, data, _ ->
                //跳转到文章页
                ArticleActivity.start(mContext, data.title, data.link)
            }
            //点击收藏,取消收藏
            setOnItemChildClickListener(R.id.articleCollectIv) { _, data, position ->
                collectDataItem = data
                collectPosition = position
                showLoading()
                if (!data.collect) {
                    viewModel.collectArticle(data.id)
                } else {
                    viewModel.uncollectArticle(data.id)
                }
            }
            //加载更多,分页加载
            setOnLoadMoreListener {
                viewModel.getArticleList(pageNum)
            }
        }

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        articleRv.run {
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration())
            adapter = articleListAdapter
            // 控制悬浮按钮
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (linearLayoutManager.findLastVisibleItemPosition() > 10) {
                        floatBtn.show()
                    } else {
                        floatBtn.hide()
                    }
                }
            })
        }

        //加载数据
        initStatusView(homeRootLayout) {
            initLoad()
        }
    }
}
