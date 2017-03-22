package io.github.droidknights.droidknightsgoods.model

import io.github.droidknights.droidknightsgoods.R
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Lotto Shuffle Test
 * Created by pluu on 2017-03-21.
 */
class LottoKtTest {

    val list = (0..10).map { Lotto(it) }

    @Test
    fun shuffleTakeList() {
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
        val resultName = arrayOf("안드로이드 서적", "안드로이드 서적", "udemy - 코틀린 강의", null)
        val resultCount = intArrayOf(6, 10, 5, 0)

        for ((idx, premium) in premiums.withIndex()) {
            val takeList = list.shuffleTakeList(premium.count)
            takeList.forEach { it ->
                it.result = premium.name
                println(idx)
            }
            assertEquals(takeList.getOrNull(0)?.result, resultName[idx])
            assertEquals(takeList.size, resultCount[idx])
        }
    }

}