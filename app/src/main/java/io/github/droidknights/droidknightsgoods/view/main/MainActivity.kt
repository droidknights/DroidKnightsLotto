package io.github.droidknights.droidknightsgoods.view.main

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.bumptech.glide.Glide
import io.github.droidknights.droidknightsgoods.R
import io.github.droidknights.droidknightsgoods.view.main.adapter.ResultAdapter
import io.github.droidknights.droidknightsgoods.view.main.presenter.MainContract
import io.github.droidknights.droidknightsgoods.view.main.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_result.*
import tech.thdev.base.view.BasePresenterActivity

class MainActivity : BasePresenterActivity<MainContract.View, MainContract.Presenter>(), MainContract.View {

    override fun onCreatePresenter() = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        val resultAdapter = ResultAdapter(this)
        recycler_view.layoutManager = GridLayoutManager(this, 3)
        recycler_view.adapter = resultAdapter

        presenter?.resultAdapterModel = resultAdapter
        presenter?.resultAdapterView = resultAdapter

        fab_next.setOnClickListener {
            presenter?.updateNextItem()
        }
        fab_prev.setOnClickListener {
            presenter?.updatePrevItem()
        }

        img_close.setOnClickListener {
            presenter?.updateRemainingCount()
        }

        content_result_include.setOnClickListener { }
    }

    override fun hideItem() {
        content_main_include.visibility = View.GONE
        content_result_include.visibility = View.GONE
        rl_done_view.visibility = View.GONE
    }

    override fun showItem(position: Int, sponsors: String, count: Int, unit: String, productName: String, imageRes: Int) {
        content_main_include.visibility = View.VISIBLE
        content_result_include.visibility = View.GONE
        rl_done_view.visibility = View.GONE

        tv_sponsors.text = ""
        if (sponsors.isNotEmpty()) {
            tv_sponsors.text = sponsors
        }
        tv_count.text = "$count$unit"
        tv_product_name.text = productName

        Glide.with(this)
                .load(imageRes)
                .fitCenter()
                .crossFade()
                .into(img_gifts)

        if (count == 0) {
            rl_done_view.visibility = View.VISIBLE
            img_gifts.setOnClickListener { }
        } else {
            img_gifts.setOnClickListener {
                presenter?.startLotto(position)
            }
        }
    }

    override fun showResultView(count: Int) {
        content_result_include.visibility = View.VISIBLE
        rl_done_view.visibility = View.GONE

        var spanCount = 3
        when (count) {
            1 -> spanCount = 1
            2 -> spanCount = 2
        }
        (recycler_view.layoutManager as GridLayoutManager).spanCount = spanCount
    }
}
