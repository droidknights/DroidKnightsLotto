package io.github.droidknights.droidknightsgoods.view.main.presenter

import android.util.Log
import io.github.droidknights.droidknightsgoods.R
import io.github.droidknights.droidknightsgoods.model.ConstLimit
import io.github.droidknights.droidknightsgoods.model.Lotto
import io.github.droidknights.droidknightsgoods.model.Premium
import io.github.droidknights.droidknightsgoods.model.shuffleTakeList
import io.github.droidknights.droidknightsgoods.view.main.adapter.model.PremiumPagerModel
import io.github.droidknights.droidknightsgoods.view.main.adapter.model.ResultAdapterContract
import tech.thdev.base.presenter.AbstractPresenter

/**
 * Created by tae-hwan on 22/03/2017.
 */

class MainPresenter : AbstractPresenter<MainContract.View>(), MainContract.Presenter {

    val list = (1..ConstLimit()).map { Lotto(it) }

    var premiums = arrayOf(
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
            _resultAdapterView.setOnClickListener { premiumIdx, isHide ->
                val premium = premiums[premiumIdx]
                if (isHide) {
                    premium.showAtOne++
                } else {
                    premium.showAtOne--
                    if (premium.showAtOne < 0) premium.showAtOne = 0
                }

                Log.d("TEMP", "isHide $isHide showAtOne ${premiums[premiumIdx].showAtOne}")
            }
        }
        get() = _resultAdapterView

    override lateinit var premiumPagerModel: PremiumPagerModel.Model
    private lateinit var _premiumPagerView: PremiumPagerModel.View
    override var premiumPagerView: PremiumPagerModel.View
        set(value) {
            _premiumPagerView = value
            _premiumPagerView.setOnClickListener {
                startLotto(it)
            }
        }
        get() = _premiumPagerView


    override fun startLotto(position: Int) {
        val premium = premiums[position]
        val takeList = list.shuffleTakeList(premium.getTotal())

        resultAdapterModel.clear()
        takeList.forEach { lotto ->
            lotto.premiumIdx = position
            if (premium.isAnimator()) {
                lotto.isAnimationNumber = true
            }
            resultAdapterModel.addItem(lotto)
        }

        view?.showResultView(premium.getTotal())
        resultAdapterView.notifyDataChange()
    }

    override fun updatePagerAdapter() {
        premiumPagerModel.itemList = premiums
    }

    override fun checkLastItem() {
        view?.showNextItem(premiums.any { it.count == it.showAtOne })
    }

    override fun changeItems() {
        premiumPagerModel.itemList = premiums
        premiumPagerView.notifyDataSetChanged()
        view?.showLastItem(premiums.size)
    }
}