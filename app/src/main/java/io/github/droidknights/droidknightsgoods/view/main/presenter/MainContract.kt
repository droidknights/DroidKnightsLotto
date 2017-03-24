package io.github.droidknights.droidknightsgoods.view.main.presenter

import io.github.droidknights.droidknightsgoods.view.main.adapter.model.PremiumPagerModel
import io.github.droidknights.droidknightsgoods.view.main.adapter.model.ResultAdapterContract
import tech.thdev.base.presenter.BasePresenter
import tech.thdev.base.presenter.BaseView

/**
 * Created by tae-hwan on 22/03/2017.
 */

interface MainContract {

    interface View : BaseView {

        fun showResultView(count: Int)

        fun hideItem()

        fun showNextItem(isAllDone: Boolean)
        fun showLastItem(position: Int)
    }

    interface Presenter : BasePresenter<View> {

        var resultAdapterModel: ResultAdapterContract.Model
        var resultAdapterView: ResultAdapterContract.View

        var premiumPagerModel: PremiumPagerModel.Model
        var premiumPagerView: PremiumPagerModel.View

        fun startLotto(position: Int)
        fun updatePagerAdapter()
        fun checkLastItem()

        fun changeItems()
    }
}