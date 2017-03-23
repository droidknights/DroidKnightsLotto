package io.github.droidknights.droidknightsgoods.view.main.presenter

import io.github.droidknights.droidknightsgoods.view.main.adapter.model.ResultAdapterContract
import tech.thdev.base.presenter.BasePresenter
import tech.thdev.base.presenter.BaseView

/**
 * Created by tae-hwan on 22/03/2017.
 */

interface MainContract {

    interface View : BaseView {

        fun showItem(position: Int, sponsors: String, count: Int, unit: String, productName: String, imageRes: Int)

        fun showResultView(count: Int)

        fun hideItem()
    }

    interface Presenter : BasePresenter<View> {

        var resultAdapterModel: ResultAdapterContract.Model
        var resultAdapterView: ResultAdapterContract.View

        fun updateNextItem()
        fun updatePrevItem()

        fun startLotto(position: Int)

        fun updateRemainingCount()
    }
}