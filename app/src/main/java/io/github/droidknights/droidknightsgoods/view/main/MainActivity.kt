package io.github.droidknights.droidknightsgoods.view.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import io.github.droidknights.droidknightsgoods.R
import io.github.droidknights.droidknightsgoods.model.Premium
import io.github.droidknights.droidknightsgoods.view.main.adapter.ResultAdapter
import io.github.droidknights.droidknightsgoods.view.main.presenter.MainContract
import io.github.droidknights.droidknightsgoods.view.main.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
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
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = resultAdapter

        presenter?.resultAdapterModel = resultAdapter
        presenter?.resultAdapterView = resultAdapter

        fab_next.setOnClickListener {
            presenter?.updateNextItem()
        }
        fab_prev.setOnClickListener {
            presenter?.updatePrevItem()
        }
    }

    override fun hideItem() {
        include.visibility = View.GONE
        rl_result_view_container.visibility = View.GONE
    }

    override fun showItem(position: Int, premium: Premium) {
        include.visibility = View.VISIBLE
        rl_result_view_container.visibility = View.GONE
        tv_sponsors.text = ""
        if (premium.sponsors.isNotEmpty()) {
            tv_sponsors.text = premium.sponsors
        }
        tv_count.text = "${premium.count}${premium.unit}"
        tv_product_name.text = premium.name
        img_gifts.setImageResource(premium.res)

        img_gifts.setOnClickListener {
            presenter?.startLotto(position)
        }
    }

    override fun showResultView() {
        rl_result_view_container.visibility = View.VISIBLE
    }
}
