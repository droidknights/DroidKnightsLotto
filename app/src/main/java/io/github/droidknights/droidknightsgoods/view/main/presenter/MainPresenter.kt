package io.github.droidknights.droidknightsgoods.view.main.presenter

import io.github.droidknights.droidknightsgoods.R
import io.github.droidknights.droidknightsgoods.model.Lotto
import io.github.droidknights.droidknightsgoods.model.Premium
import tech.thdev.base.presenter.AbstractPresenter

/**
 * Created by tae-hwan on 22/03/2017.
 */

class MainPresenter : AbstractPresenter<MainContract.View>(), MainContract.Presenter {

    val premiums = arrayOf(
            Premium("후원 : 제이펍", "안드로이드 서적", 6, "권", R.drawable.books),
            Premium("후원 : 한빛미디어", "안드로이드 서적", 10, "권", R.drawable.books),
            Premium("제공 : 권태환", "udemy - 코틀린 강의", 5, "장", R.drawable.kotlin_logo),
            Premium("제공 : 박민우", "블루투스 키보드 + 마우스", 1, "개", R.drawable.keyboard_mouse),
            Premium("제공 : 가나초코", "안드로이드 피규어", 10, "개", R.drawable.android_figurine),
            Premium("", "타조쿠션", 3, "개", R.drawable.ostrich_cushion),
            Premium("", "레오폴드 FC750R", 1, "개", R.drawable.fc750r),
            Premium("", "레오폴드 FC980C", 1, "개", R.drawable.fc980c),
            Premium("", "G Watch Style", 1, "개", R.drawable.lg_watch))

    lateinit override var lotto: Lotto

    override fun updateProduct(position: Int) {
        if (position == -1) {
            view?.hideItem()
        }
        if (position > -1 && position < premiums.size) {
            view?.showItem(premiums[position])
        }
    }

    override fun startLotto(productNumber: Int) {

    }
}