package io.github.droidknights.droidknightsgoods.model

/**
 * 경품 정보 Class
 * Created by pluu on 2017-03-20.
 */
data class Premium(val sponsors: String,
                   val name: String,
                   val count : Int,
                   val unit: String,
                   val res: Int,
                   var showAtOne: Int = 0)