package io.github.droidknights.droidknightsgoods.view.main

import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import io.github.droidknights.droidknightsgoods.R
import io.github.droidknights.droidknightsgoods.view.anim.ZoomOutPageTransformer
import io.github.droidknights.droidknightsgoods.view.main.adapter.PremiumPagerAdapter
import io.github.droidknights.droidknightsgoods.view.main.adapter.ResultAdapter
import io.github.droidknights.droidknightsgoods.view.main.presenter.MainContract
import io.github.droidknights.droidknightsgoods.view.main.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_result.*
import tech.thdev.base.view.BasePresenterActivity
import android.support.customtabs.CustomTabsIntent



class MainActivity : BasePresenterActivity<MainContract.View, MainContract.Presenter>(), MainContract.View {

    override fun onCreatePresenter() = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFullScreen()

        val resultAdapter = ResultAdapter(this)
        recycler_view.layoutManager = GridLayoutManager(this, 3) as RecyclerView.LayoutManager?
        recycler_view.adapter = resultAdapter

        presenter?.resultAdapterModel = resultAdapter
        presenter?.resultAdapterView = resultAdapter

        val pagerAdapter = PremiumPagerAdapter(supportFragmentManager)
        presenter?.premiumPagerModel = pagerAdapter
        presenter?.premiumPagerView = pagerAdapter
        presenter?.updatePagerAdapter()
        view_pager.adapter = pagerAdapter
        view_pager.setPageTransformer(true, ZoomOutPageTransformer())

        content_result_include.setOnClickListener { }

        img_logo.setOnClickListener {
            setFullScreen()
            presenter?.checkLastItem()
        }

        img_close.setOnClickListener {
            pagerAdapter.notifyDataSetChanged()
            content_result_include.visibility = View.GONE
        }

        img_logo_text.setOnClickListener {
            hideItem()
        }

        btn_open_source_license.setOnClickListener {
            // Use a CustomTabsIntent.Builder to configure CustomTabsIntent.
            // Once ready, call CustomTabsIntent.Builder.build() to create a CustomTabsIntent
            // and launch the desired Url with CustomTabsIntent.launchUrl()
            val builder = CustomTabsIntent.Builder()
            // Changes the background color for the omnibox. colorInt is an int
            // that specifies a Color.
            builder.setToolbarColor(resources.getColor(R.color.colorPrimary))

            builder.setStartAnimations(this, 0, 0)
            builder.setExitAnimations(this, 0, 0)

            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse("https://github.com/droidknights/DroidKnightsLotto/blob/master/Open-source-license.md"))
        }
    }

    private fun setFullScreen() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }

    override fun hideItem() {
        img_logo.alpha = 1f
        view_pager.visibility = View.GONE
        img_logo_text.visibility = View.GONE
        tv_message.visibility = View.GONE
        content_result_include.visibility = View.GONE
    }

    override fun showResultView(count: Int) {
        content_result_include.visibility = View.VISIBLE

        var spanCount = 3
        when (count) {
            1 -> spanCount = 1
            2 -> spanCount = 2
        }
        (recycler_view.layoutManager as GridLayoutManager).spanCount = spanCount
    }

    override fun showNextItem(isAllDone: Boolean) {
        if (isAllDone) {
            img_logo.alpha = 0f
            tv_message.visibility = View.VISIBLE
            tv_message.setOnClickListener {
                presenter?.changeItems()
            }
            img_logo.setOnClickListener { }

        } else {
            img_logo.alpha = 0.5f
            view_pager.visibility = View.VISIBLE
            img_logo_text.visibility = View.VISIBLE
        }
    }

    override fun showLastItem(position: Int) {
        img_logo.alpha = 0.5f
        tv_message.visibility = View.GONE
        view_pager.visibility = View.VISIBLE
        img_logo_text.visibility = View.VISIBLE
        view_pager.setCurrentItem(position, true)
    }
}
