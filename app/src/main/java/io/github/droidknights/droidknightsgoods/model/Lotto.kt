package io.github.droidknights.droidknightsgoods.model

import java.util.*

/**
 * Lotto Data Class
 * Created by pluu on 2017-03-20.
 */
data class Lotto(val idx: Int, var result : String? = null)

fun List<Lotto>.shuffleTakeList(size: Int): List<Lotto> {
    with(this) {
        val sliced = filter { (_, result) -> result == null }
                .toList()
        Collections.shuffle(sliced, Random(System.currentTimeMillis()))
        return sliced.take(size)
    }
}