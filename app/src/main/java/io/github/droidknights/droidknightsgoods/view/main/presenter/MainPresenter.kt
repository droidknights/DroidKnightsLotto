package io.github.droidknights.droidknightsgoods.view.main.presenter

import io.github.droidknights.droidknightsgoods.R
import io.github.droidknights.droidknightsgoods.model.Lotto
import io.github.droidknights.droidknightsgoods.model.Premium
import io.github.droidknights.droidknightsgoods.model.shuffleTakeList
import io.github.droidknights.droidknightsgoods.view.main.adapter.model.ResultAdapterContract
import tech.thdev.base.presenter.AbstractPresenter

/**
 * Created by tae-hwan on 22/03/2017.
 */

class MainPresenter : AbstractPresenter<MainContract.View>(), MainContract.Presenter {

    private var nowPosition = -1

    val list = (1..200).map { Lotto(it) }

    val premiums = arrayOf(
            Premium("후원 - 제이펍", "안드로이드 서적", 6, "권", R.drawable.books),
            Premium("후원 - 한빛미디어", "안드로이드 서적", 10, "권", R.drawable.books),
            Premium("제공 - 권태환", "udemy - 코틀린 강의", 5, "장", R.drawable.kotlin_logo),
            Premium("제공 - 박민우", "블루투스 키보드 + 마우스", 1, "개", R.drawable.keyboard_mouse),
            Premium("제공 - 가나초코", "안드로이드 피규어", 10, "개", R.drawable.android_figurine),
            Premium("", "타조쿠션", 3, "개", R.drawable.ostrich_cushion),
            Premium("", "레오폴드 FC750R", 1, "개", R.drawable.fc750r),
            Premium("", "레오폴드 FC980C", 1, "개", R.drawable.fc980c),
            Premium("", "G Watch Style", 1, "개", R.drawable.lg_watch))

    lateinit override var resultAdapterModel: ResultAdapterContract.Model
    private lateinit var _resultAdapterView: ResultAdapterContract.View
    override var resultAdapterView: ResultAdapterContract.View
        set(value) {
            _resultAdapterView = value
        }
        get() = _resultAdapterView

    override fun updateNextItem() {
        ++nowPosition
        updateNowItem()
    }

    override fun updatePrevItem() {
        --nowPosition
        updateNowItem()
    }

    private fun updateNowItem() {
        if (nowPosition < -1) {
            nowPosition = -1
        } else if (nowPosition >= premiums.size) {
            nowPosition = premiums.size - 1
        }

        if (nowPosition == -1) {
            view?.hideItem()
        } else {
            view?.showItem(nowPosition, premiums[nowPosition])
        }
    }

    override fun startLotto(position: Int) {
        val premium = premiums[position]
        val takeList = list.shuffleTakeList(premium.count - premium.showAtOne)

        resultAdapterModel.clear()
        takeList.forEach { lotto ->
            lotto.result = premium.name
            resultAdapterModel.addItem(lotto)
        }

        view?.showResultView()
        resultAdapterView.notifyDataChange()
    }
}