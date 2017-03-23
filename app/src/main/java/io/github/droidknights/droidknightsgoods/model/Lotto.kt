package io.github.droidknights.droidknightsgoods.model

import java.util.*

/**
 * Lotto Data Class
 * Created by pluu on 2017-03-20.
 */
data class Lotto(val idx: Int, var premiumIdx: Int = -1, var isInterval: Boolean = false)

fun List<Lotto>.shuffleTakeList(size: Int): List<Lotto> {
    with(this) {
        val sliced = filter { (_, premiumIdx) -> premiumIdx == -1 }
                .toList()
        Collections.shuffle(sliced, Random(System.nanoTime()))
        return sliced.take(size)
    }
}