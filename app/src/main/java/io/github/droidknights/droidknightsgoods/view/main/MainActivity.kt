package io.github.droidknights.droidknightsgoods.view.main

import android.os.Bundle
import android.view.View
import io.github.droidknights.droidknightsgoods.R
import io.github.droidknights.droidknightsgoods.model.Premium
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

        fab_next.setOnClickListener {  }
        fab_prev.setOnClickListener {  }
    }

    override fun hideItem() {
        include.visibility = View.GONE
    }

    override fun showItem(premium: Premium) {
        include.visibility = View.VISIBLE
        tv_sponsors.text = ""
        if (premium.sponsors.isNotEmpty()) {
            tv_sponsors.text = premium.sponsors
        }
        tv_count.text = "${premium.count}${premium.unit}"
        tv_product_name.text = premium.name
    }

    override fun showNumber(number: String) {

    }
}
