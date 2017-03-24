package io.github.droidknights.droidknightsgoods.view.main.adapter.model

import io.github.droidknights.droidknightsgoods.model.Premium

/**
 * Created by Tae-hwan on 24/03/2017.
 */

interface PremiumPagerModel {

    interface View {

        fun setOnClickListener(onClick: (Int) -> Unit)
        fun notifyDataSetChanged()
    }

    interface Model {

        var itemList: Array<Premium>
    }
}