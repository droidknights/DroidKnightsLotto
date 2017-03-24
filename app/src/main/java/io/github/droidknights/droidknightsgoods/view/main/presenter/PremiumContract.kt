package io.github.droidknights.droidknightsgoods.view.main.presenter

import io.github.droidknights.droidknightsgoods.model.Premium
import tech.thdev.base.presenter.BasePresenter
import tech.thdev.base.presenter.BaseView

/**
 * Created by Tae-hwan on 24/03/2017.
 */

interface PremiumContract {

    interface View : BaseView {

        fun showItem(sponsors: String, count: Int, unit: String, productName: String, imageRes: Int)
    }

    interface Presenter : BasePresenter<View> {
        fun updateView(premium: Premium)
    }
}