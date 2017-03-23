package io.github.droidknights.droidknightsgoods.model

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
                Premium("","경품A", 2, "", 0),
                Premium("", "경품B", 1, "", 0),
                Premium("", "경품C", 10, "", 0),
                Premium("", "경품D", 5, "", 0))
        val resultName = arrayOf(0, 1, 2, null)
        val resultCount = intArrayOf(2, 1, 8, 0)

        for ((idx, premium) in premiums.withIndex()) {
            val takeList = list.shuffleTakeList(premium.count)
            takeList.forEach { it ->
                it.premiumIdx = idx
            }
            assertEquals(takeList.getOrNull(0)?.premiumIdx, resultName[idx])
            assertEquals(takeList.size, resultCount[idx])
        }
    }
}
