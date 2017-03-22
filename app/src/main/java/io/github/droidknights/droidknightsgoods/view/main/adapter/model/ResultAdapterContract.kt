package io.github.droidknights.droidknightsgoods.view.main.adapter.model

import io.github.droidknights.droidknightsgoods.model.Lotto

/**
 * Created by tae-hwan on 23/03/2017.
 */

interface ResultAdapterContract {

    interface View {
        fun notifyDataChange()
    }

    interface Model {

        fun clear()

        fun addItem(lotto: Lotto)
    }
}