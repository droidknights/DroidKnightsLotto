package io.github.droidknights.droidknightsgoods.view.main.presenter

import io.github.droidknights.droidknightsgoods.model.Lotto
import io.github.droidknights.droidknightsgoods.model.Premium
import tech.thdev.base.presenter.BasePresenter
import tech.thdev.base.presenter.BaseView

/**
 * Created by tae-hwan on 22/03/2017.
 */

interface MainContract {

    interface View : BaseView {

        fun showItem(premium: Premium)

        fun showNumber(number: String)

        fun hideItem()
    }

    interface Presenter : BasePresenter<View> {

        var lotto: Lotto

        fun updateProduct(position: Int)

        fun startLotto(productNumber: Int)
    }
}