package io.github.droidknights.droidknightsgoods.view.main.presenter

import io.github.droidknights.droidknightsgoods.model.Premium
import tech.thdev.base.presenter.AbstractPresenter

/**
 * Created by Tae-hwan on 24/03/2017.
 */

class PremiumPresenter : AbstractPresenter<PremiumContract.View>(), PremiumContract.Presenter {

    override fun updateView(premium: Premium) {
        android.util.Log.d("TEMP", "position $premium")
        view?.showItem(premium.sponsors, premium.getTotal(), premium.unit, premium.name, premium.res)
    }
}