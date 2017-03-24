package io.github.droidknights.droidknightsgoods.view.main.adapter

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import io.github.droidknights.droidknightsgoods.listener.OnPremiumClickListener
import io.github.droidknights.droidknightsgoods.model.Premium
import io.github.droidknights.droidknightsgoods.view.main.PremiumFragment
import io.github.droidknights.droidknightsgoods.view.main.adapter.model.PremiumPagerModel

/**
 * Created by Tae-hwan on 24/03/2017.
 */

class PremiumPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager),
        PremiumPagerModel.Model, PremiumPagerModel.View {

    private lateinit var onPremiumClickListener: OnPremiumClickListener

    override fun setOnClickListener(onClick: (Int) -> Unit) {
        onPremiumClickListener = object : OnPremiumClickListener {

            override fun onClick(position: Int) {
                onClick(position)
            }
        }
    }

    override lateinit var itemList: Array<Premium>

    override fun getCount(): Int {
        println("itemList.size ${itemList.size}")
        return itemList.size
    }

    override fun getItem(position: Int) = PremiumFragment.create(position, itemList[position], onPremiumClickListener)

    override fun getItemPosition(`object`: Any?) = PagerAdapter.POSITION_NONE
}